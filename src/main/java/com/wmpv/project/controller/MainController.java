package com.wmpv.project.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wmpv.project.dto.ClienteDto;
import com.wmpv.project.entity.ClienteEntity;
import com.wmpv.project.enums.EnumCatalog;
import com.wmpv.project.enums.EnumResponse;
import com.wmpv.project.model.ResponseData;
import com.wmpv.project.model.ResponseModel;
import com.wmpv.project.service.ClienteEntityService;
import com.wmpv.project.util.EntityDtoConverter;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class MainController {
	
	@Autowired
	ClienteEntityService clienteService;

	@GetMapping(value = "/testdocker")
    public ResponseEntity<ResponseModel> testDocker() {
    	ResponseModel response = new ResponseModel(EnumResponse.OK.code());
    	try {
    		response.setMessage("Test docker response!");
		} catch (Exception e) {
			response.setCode(EnumResponse.ERROR.code());
			response.setMessage(e.getMessage());
		}
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@GetMapping(value = "/clientes/{page}/{size}")
    public ResponseEntity<ResponseData<List<ClienteDto>>> getClients(@PathVariable("page") final Integer page, @PathVariable("size") final Integer size) {
    	ResponseData<List<ClienteDto>> response = new ResponseData<>(EnumResponse.OK.code());
    	try {
    		Page<ClienteEntity> pages = clienteService.findByIdentificacion(PageRequest.of(page, size, Sort.by("idCliente").ascending()), null);
    		response.setData(pages.getContent().stream().map(r -> EntityDtoConverter.clienteDto.apply(r)).collect(Collectors.toList()));
    		response.setMessage("Consulta exitosa!");
		} catch (Exception e) {
			response.setCode(EnumResponse.ERROR.code());
			response.setMessage(e.getMessage());
		}
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@PostMapping(value = "/clientes")
    public ResponseEntity<ResponseModel> postClients(@RequestBody ClienteDto request) {
    	ResponseModel response = new ResponseModel(EnumResponse.OK.code());
    	try {
    		clienteService.saveOrUpdate(EntityDtoConverter.clienteEntity.apply(request));
    		response.setMessage("Registro exitoso!");
		} catch (Exception e) {
			response.setCode(EnumResponse.ERROR.code());
			response.setMessage(e.getMessage());
		}
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@DeleteMapping(value = "/clientes")
    public ResponseEntity<ResponseModel> deleteClients(@RequestBody ClienteDto request) {
    	ResponseModel response = new ResponseModel(EnumResponse.OK.code());
    	try {
    		request.setStatus(EnumCatalog.INACTIVO.code());
    		clienteService.saveOrUpdate(EntityDtoConverter.clienteEntity.apply(request));
    		response.setMessage("Eliminado exitoso!");
		} catch (Exception e) {
			response.setCode(EnumResponse.ERROR.code());
			response.setMessage(e.getMessage());
		}
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
