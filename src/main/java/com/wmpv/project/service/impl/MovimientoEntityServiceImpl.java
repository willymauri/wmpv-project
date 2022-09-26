package com.wmpv.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.wmpv.project.entity.MovimientoEntity;
import com.wmpv.project.exception.WsServiceException;
import com.wmpv.project.repository.MovimientoEntityRepository;
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
	public void saveOrUpdate(MovimientoEntity entity) throws WsServiceException {
		try {
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

}
