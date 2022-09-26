package com.wmpv.project.service;

import java.util.List;

import com.wmpv.project.entity.CuentaEntity;
import com.wmpv.project.exception.WsServiceException;

/**
 * @version 1.0
 * @autor william.patino
 **/
public interface CuentaEntityService {
	
	CuentaEntity findByCod(Integer id) throws WsServiceException ;

	List<CuentaEntity> findByCliente(Integer idCliente) throws WsServiceException ;

	void saveOrUpdate(CuentaEntity entity) throws WsServiceException ;
	
	void delete(CuentaEntity entity) throws WsServiceException ;
}
