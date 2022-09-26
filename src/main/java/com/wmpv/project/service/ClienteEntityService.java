package com.wmpv.project.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wmpv.project.entity.ClienteEntity;
import com.wmpv.project.exception.WsServiceException;

/**
 * @version 1.0
 * @autor william.patino
 **/
public interface ClienteEntityService {

	ClienteEntity findByCod(Integer id) throws WsServiceException ;
    
	Page<ClienteEntity> findByIdentificacion(Pageable pageable, String identificacion) throws WsServiceException ;
	
	List<ClienteEntity> findByEstado(String estado) throws WsServiceException ;

	void saveOrUpdate(ClienteEntity entity) throws WsServiceException ;
	
	void delete(ClienteEntity entity) throws WsServiceException ;
}
