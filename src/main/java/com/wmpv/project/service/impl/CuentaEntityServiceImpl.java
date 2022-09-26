package com.wmpv.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wmpv.project.entity.CuentaEntity;
import com.wmpv.project.exception.WsServiceException;
import com.wmpv.project.repository.CuentaEntityRepository;
import com.wmpv.project.service.CuentaEntityService;
import com.wmpv.project.util.Validate;

import lombok.extern.slf4j.Slf4j;

/**
 * @version 1.0
 * @autor william.patino
 **/
@Component
@Slf4j
public class CuentaEntityServiceImpl implements CuentaEntityService {

    @Autowired
    private final CuentaEntityRepository repository;

    CuentaEntityServiceImpl(CuentaEntityRepository repository) {
        this.repository = repository;
    }

	@Override
	public List<CuentaEntity> findByCliente(Integer idCliente) throws WsServiceException {
		try {
            return repository.findByCliente(idCliente);
        } catch (Exception e) {
            throw new WsServiceException("Problema consulta cuenta por cliente: "+e.getMessage());
        }
	}

	@Override
	public void saveOrUpdate(CuentaEntity entity) throws WsServiceException {
		try {
			repository.save(entity);
        } catch (Exception e) {
            throw new WsServiceException("Problema al crear/actualizar cuenta por cliente: "+e.getMessage());
        }
	}

	@Override
	public void delete(CuentaEntity entity) throws WsServiceException {
		try {
			repository.delete(entity);
        } catch (Exception e) {
            throw new WsServiceException("Problema al eliminar cuenta por cliente: "+e.getMessage());
        }
	}

	@Override
	public CuentaEntity findByCod(Integer id) throws WsServiceException {
		try {
    		CuentaEntity tipo = repository.findByCod(id);
    		Validate.notFound(tipo);
    		return tipo;
        } catch (Exception e) {
            throw new WsServiceException("Problema consulta cuenta: "+id+" "+e.getMessage());
        }
	}

}
