package com.wmpv.project.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wmpv.project.entity.ClienteEntity;
import com.wmpv.project.exception.WsServiceException;

/**
 * @version 1.0
 * @autor william.patino
 **/
@Repository
public interface ClienteEntityRepository extends CrudRepository<ClienteEntity, Integer> {

	@Query("select cs from ClienteEntity cs where cs.idCliente = :id")
	ClienteEntity findByCod(@Param("id")Integer id) throws WsServiceException ;
    
	@Query("select cs from ClienteEntity cs where cs.identificacion = COALESCE(:identificacion, cs.identificacion) ")
	Page<ClienteEntity> findByIdentificacion(Pageable pageable, @Param("identificacion")String identificacion) throws WsServiceException ;
    
	@Query("select cs from ClienteEntity cs where cs.estado = :estado")
	List<ClienteEntity> findByEstado(@Param("estado")String estado) throws WsServiceException ;
}
