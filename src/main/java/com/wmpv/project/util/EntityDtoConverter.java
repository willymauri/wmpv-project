package com.wmpv.project.util;

import java.util.Objects;
import java.util.function.Function;

import com.wmpv.project.dto.ClienteDto;
import com.wmpv.project.entity.ClienteEntity;
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
}
