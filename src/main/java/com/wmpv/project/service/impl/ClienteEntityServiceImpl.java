package com.wmpv.project.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.wmpv.project.entity.ClienteEntity;
import com.wmpv.project.exception.WsServiceException;
import com.wmpv.project.repository.ClienteEntityRepository;
import com.wmpv.project.service.ClienteEntityService;
import com.wmpv.project.util.Validate;
import lombok.extern.slf4j.Slf4j;

/**
 * @version 1.0
 * @autor william.patino
 **/
@Component
@Slf4j
public class ClienteEntityServiceImpl implements ClienteEntityService {

    @Autowired
    private final ClienteEntityRepository repository;

    ClienteEntityServiceImpl(ClienteEntityRepository repository) {
        this.repository = repository;
    }

    @Override
	public ClienteEntity findByCod(Integer id) throws WsServiceException {
    	try {
    		ClienteEntity tipo = repository.findByCod(id);
    		Validate.notFound(tipo);
    		return tipo;
        } catch (Exception e) {
            throw new WsServiceException("Problema consulta cliente: "+id+" "+e.getMessage());
        }
	}


	@Override
	public Page<ClienteEntity> findByIdentificacion(Pageable pageable, String identificacion) throws WsServiceException {
		try {
            return repository.findByIdentificacion(pageable, (!Objects.isNull(identificacion) && !identificacion.isEmpty())?"%"+identificacion+"%":null); 
        } catch (Exception e) {
            throw new WsServiceException("Problema consulta cliente id: "+identificacion+" "+e.getMessage());
        }
	}

	@Override
	public List<ClienteEntity> findByEstado(String estado) throws WsServiceException {
		try {
            return repository.findByEstado(estado);
        } catch (Exception e) {
            throw new WsServiceException("Problema consulta cliente estado: "+e.getMessage());
        }
	}

	@Override
	public void saveOrUpdate(ClienteEntity entity) throws WsServiceException {
		try {
			repository.save(entity);
        } catch (Exception e) {
            throw new WsServiceException("Problema al crear/actualizar cliente: "+e.getMessage());
        }
	}

	@Override
	public void delete(ClienteEntity entity) throws WsServiceException {
		try {
			repository.delete(entity);
        } catch (Exception e) {
            throw new WsServiceException("Problema al eliminar cliente: "+e.getMessage());
        }
	}

}
