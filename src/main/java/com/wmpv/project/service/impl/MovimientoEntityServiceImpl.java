package com.wmpv.project.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.wmpv.project.entity.CuentaEntity;
import com.wmpv.project.entity.MovimientoEntity;
import com.wmpv.project.exception.WsServiceException;
import com.wmpv.project.repository.MovimientoEntityRepository;
import com.wmpv.project.service.CuentaEntityService;
import com.wmpv.project.service.MovimientoEntityService;

import lombok.extern.slf4j.Slf4j;

/**
 * @version 1.0
 * @autor william.patino
 **/
@Component
@Slf4j
public class MovimientoEntityServiceImpl implements MovimientoEntityService {

    @Autowired
    private final MovimientoEntityRepository repository;

    @Autowired
    CuentaEntityService cuentaService;
    
    @Autowired
    Environment env;
    
    MovimientoEntityServiceImpl(MovimientoEntityRepository repository) {
        this.repository = repository;
    }


	@Override
	public Page<MovimientoEntity> findByCuenta(Pageable pageable, Integer idCuenta) throws WsServiceException {
		try {
            return repository.findByCuenta(pageable, idCuenta); 
        } catch (Exception e) {
            throw new WsServiceException("Problema consulta movimiento por cuenta: "+idCuenta+" "+e.getMessage());
        }
	}

	@Override
	public List<MovimientoEntity> findByCuenta(Integer idCuenta) throws WsServiceException {
		try {
            return repository.findByCuenta(idCuenta);
        } catch (Exception e) {
            throw new WsServiceException("Problema consulta cliente estado: "+e.getMessage());
        }
	}

	@Override
	public void credito(MovimientoEntity entity) throws WsServiceException {
		try {
			Page<MovimientoEntity> move = repository.findByCuenta(PageRequest.of(0, 3, Sort.by("idMovimiento").descending()), entity.getCuenta().getIdCuenta());
			if(!move.getContent().isEmpty()) {
				MovimientoEntity m = move.getContent().get(0);
				entity.setSaldo(m.getSaldo().add(entity.getValor()));
			} else {
				CuentaEntity c = cuentaService.findByCod(entity.getCuenta().getIdCuenta());
				entity.setSaldo(c.getSaldoInicial().add(entity.getValor()));
			}
			repository.save(entity);
        } catch (Exception e) {
            throw new WsServiceException("Problema al crear/actualizar movimiento por cuenta: "+e.getMessage());
        }
	}

	@Override
	public void debito(MovimientoEntity entity) throws WsServiceException {
		try {
			String limite = env.getProperty("limite.diario");
			Page<MovimientoEntity> move = repository.findByCuenta(PageRequest.of(0, 3, Sort.by("idMovimiento").descending()), entity.getCuenta().getIdCuenta());
			if(!move.getContent().isEmpty()) {
				MovimientoEntity m = move.getContent().get(0);
				if(entity.getValor().compareTo(new BigDecimal(limite)) > 0) {
					throw new WsServiceException("Limite excedido para el debito.");
				}
				if(m.getSaldo().compareTo(entity.getValor()) < 0) {
					throw new WsServiceException("Saldo no disponible para el debito.");
				}
				entity.setSaldo(m.getSaldo().subtract(entity.getValor()));
			} else {
				CuentaEntity c = cuentaService.findByCod(entity.getCuenta().getIdCuenta());
				if(entity.getValor().compareTo(new BigDecimal(limite)) > 0) {
					throw new WsServiceException("Limite excedido para el debito.");
				}
				if(c.getSaldoInicial().compareTo(entity.getValor()) < 0) {
					throw new WsServiceException("Saldo no disponible para el debito.");
				}
				entity.setSaldo(c.getSaldoInicial().subtract(entity.getValor()));
			}
			entity.setValor(entity.getValor().multiply(new BigDecimal(-1)));
			repository.save(entity);
        } catch (Exception e) {
            throw new WsServiceException("Problema al crear/actualizar movimiento por cuenta: "+e.getMessage());
        }
	}
	
	@Override
	public void delete(MovimientoEntity entity) throws WsServiceException {
		try {
			repository.delete(entity);
        } catch (Exception e) {
            throw new WsServiceException("Problema al eliminar movimiento: "+e.getMessage());
        }
	}


	@Override
	public List<MovimientoEntity> findByDateCliente(Date init, Date end, String id) throws WsServiceException {
		try {
			return repository.findByDateCliente(init, end, id);
        } catch (Exception e) {
            throw new WsServiceException("Problema al generar reporte: "+e.getMessage());
        }
	}

}
