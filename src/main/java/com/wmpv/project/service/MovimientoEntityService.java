package com.wmpv.project.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wmpv.project.entity.MovimientoEntity;
import com.wmpv.project.exception.WsServiceException;

/**
 * @version 1.0
 * @autor william.patino
 **/
public interface MovimientoEntityService {

	Page<MovimientoEntity> findByCuenta(Pageable pageable, Integer idCuenta) throws WsServiceException ;
	
	List<MovimientoEntity> findByCuenta(Integer idCuenta) throws WsServiceException ;
	
	List<MovimientoEntity> findByDateCliente(Date init, Date end, String id) throws WsServiceException ;

	void credito(MovimientoEntity entity) throws WsServiceException ;
	
	void debito(MovimientoEntity entity) throws WsServiceException ;
	
	void delete(MovimientoEntity entity) throws WsServiceException ;
}
