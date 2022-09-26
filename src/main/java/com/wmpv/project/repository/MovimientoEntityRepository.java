package com.wmpv.project.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wmpv.project.entity.MovimientoEntity;
import com.wmpv.project.exception.WsServiceException;

/**
 * @version 1.0
 * @autor william.patino
 **/
@Repository
public interface MovimientoEntityRepository extends CrudRepository<MovimientoEntity, Integer> {

	@Query("select cs from MovimientoEntity cs where cs.cuenta.idCuenta = :idCuenta")
	List<MovimientoEntity> findByCuenta(@Param("idCuenta")Integer idCuenta) throws WsServiceException ;
	
	@Query("select cs from MovimientoEntity cs where cs.cuenta.idCuenta = :idCuenta ")
	Page<MovimientoEntity> findByCuenta(Pageable pageable, @Param("idCuenta")Integer idCuenta) throws WsServiceException ;
	
	@Query("select cs from MovimientoEntity cs where cs.fecha between :initDate and :endDate and cs.cuenta.cliente.identificacion = :id")
	List<MovimientoEntity> findByDateCliente(@Param("initDate")Date init, @Param("endDate")Date end, @Param("id")String id) throws WsServiceException ;
}
