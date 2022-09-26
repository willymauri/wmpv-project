package com.wmpv.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wmpv.project.entity.CuentaEntity;
import com.wmpv.project.exception.WsServiceException;

/**
 * @version 1.0
 * @autor william.patino
 **/
@Repository
public interface CuentaEntityRepository extends CrudRepository<CuentaEntity, Integer> {

	@Query("select cs from CuentaEntity cs where cs.idCuenta = :id")
	CuentaEntity findByCod(@Param("id")Integer id) throws WsServiceException ;
	
	@Query("select cs from CuentaEntity cs where cs.cliente.idCliente = :idCliente")
	List<CuentaEntity> findByCliente(@Param("idCliente")Integer idCliente) throws WsServiceException ;
}
