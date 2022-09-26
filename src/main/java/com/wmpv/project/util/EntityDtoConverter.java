package com.wmpv.project.util;

import java.util.Date;
import java.util.Objects;
import java.util.function.Function;

import com.wmpv.project.dto.ClienteDto;
import com.wmpv.project.dto.CuentaDto;
import com.wmpv.project.dto.MovimientoDto;
import com.wmpv.project.dto.ReporteDto;
import com.wmpv.project.entity.ClienteEntity;
import com.wmpv.project.entity.CuentaEntity;
import com.wmpv.project.entity.MovimientoEntity;
import com.wmpv.project.enums.EnumCatalog;

/**
 * @version 1.0
 * @autor william.patino
 **/
public class EntityDtoConverter {

	/**
     * 
     */
    public static Function<ClienteEntity, ClienteDto> clienteDto = entity -> {
    	var dto = new ClienteDto();
    	if(!Objects.isNull(entity)) {
    		dto.setCode(entity.getIdCliente());
    		dto.setId(entity.getIdentificacion());
    		dto.setName(entity.getNombre());
    		dto.setAddress(entity.getDireccion());
    		dto.setGender(entity.getGenero());
    		dto.setPhone(entity.getTelefono());
    		dto.setAge(entity.getEdad());
    		dto.setPass(entity.getContrasena());
    		dto.setStatus(entity.getEstado());
    	}
        return dto;
    };
    
    /**
     * 
     */
    public static Function<ClienteDto, ClienteEntity> clienteEntity = dto -> {
    	var entity = new ClienteEntity();
    	if(!Objects.isNull(dto)) {
    		entity.setIdCliente(dto.getCode());
    		entity.setIdentificacion(dto.getId());
    		entity.setNombre(dto.getName());
    		entity.setDireccion(dto.getAddress());
    		entity.setGenero(dto.getGender());
    		entity.setTelefono(dto.getPhone());
    		entity.setEdad(dto.getAge());
    		entity.setContrasena(dto.getPass());
    		if(Objects.isNull(dto.getCode()))
    			entity.setEstado(EnumCatalog.ACTIVO.code());
    		entity.setEstado(dto.getStatus());
    	}
        return entity;
    };
    
    /**
     * 
     */
    public static Function<CuentaEntity, CuentaDto> cuentaDto = entity -> {
    	var dto = new CuentaDto();
    	if(!Objects.isNull(entity)) {
    		dto.setIdAccount(entity.getIdCuenta());
    		dto.setIdClient(entity.getCliente().getIdCliente());
    		dto.setNumberAccount(entity.getNumero());
    		dto.setTypeAccount(entity.getTipoCuenta());
    		dto.setInitBalance(entity.getSaldoInicial());
    		dto.setStatus(entity.getEstado());
    	}
        return dto;
    };
    
    /**
     * 
     */
    public static Function<CuentaDto, CuentaEntity> cuentaEntity = dto -> {
    	var entity = new CuentaEntity();
    	
    	if(!Objects.isNull(dto)) {
    		entity.setIdCuenta(dto.getIdAccount());
    		entity.setCliente(new ClienteEntity());
    		entity.getCliente().setIdCliente(dto.getIdClient());
    		entity.setNumero(dto.getNumberAccount());
    		entity.setTipoCuenta(dto.getTypeAccount());
    		entity.setSaldoInicial(dto.getInitBalance());
    		entity.setEstado(dto.getStatus());
    	}
        return entity;
    };
    
    /**
     * 
     */
    public static Function<MovimientoEntity, MovimientoDto> moveDto = entity -> {
    	var dto = new MovimientoDto();
    	if(!Objects.isNull(entity)) {
    		dto.setType(entity.getTipo());
    		dto.setDate(entity.getFecha());
    		dto.setBalance(entity.getSaldo());
    		dto.setValue(entity.getValor());
    		dto.setIdAccount(entity.getCuenta().getIdCuenta());
    	}
        return dto;
    };
    
    /**
     * 
     */
    public static Function<MovimientoDto, MovimientoEntity> moveEntity = dto -> {
    	var entity = new MovimientoEntity();
    	
    	if(!Objects.isNull(dto)) {
    		entity.setFecha(new Date());
    		entity.setTipo(dto.getType());
    		entity.setSaldo(dto.getBalance());
    		entity.setValor(dto.getValue());
    		entity.setCuenta(new CuentaEntity());
    		entity.getCuenta().setIdCuenta(dto.getIdAccount());
    	}
        return entity;
    };
    
    /**
     * 
     */
    public static Function<MovimientoEntity, ReporteDto> reporteDto = entity -> {
    	var dto = new ReporteDto();
    	if(!Objects.isNull(entity)) {
    		dto.setFecha(entity.getFecha());
    		dto.setCliente(entity.getCuenta().getCliente().getNombre());
    		dto.setNumeroCuenta(entity.getCuenta().getNumero());
    		dto.setTipoCuenta(entity.getCuenta().getTipoCuenta().equals("H")?"Ahorros":"Corriente");
    		dto.setSaldoInicial(entity.getCuenta().getSaldoInicial());
    		dto.setSaldoDisponible(entity.getValor());
    		dto.setEstado(entity.getCuenta().getEstado().equals("A")?"Activo":"Inactivo");
    	}
        return dto;
    };
}
