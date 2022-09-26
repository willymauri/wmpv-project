package com.wmpv.project.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.wmpv.project.dto.CuentaDto;
import com.wmpv.project.dto.MovimientoDto;
import com.wmpv.project.dto.ReporteDto;
import com.wmpv.project.entity.ClienteEntity;
import com.wmpv.project.entity.CuentaEntity;
import com.wmpv.project.entity.MovimientoEntity;
import com.wmpv.project.enums.EnumCatalog;
import com.wmpv.project.enums.EnumResponse;
import com.wmpv.project.model.ResponseData;
import com.wmpv.project.model.ResponseModel;
import com.wmpv.project.service.ClienteEntityService;
import com.wmpv.project.service.CuentaEntityService;
import com.wmpv.project.service.MovimientoEntityService;
import com.wmpv.project.util.EntityDtoConverter;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class MainController {
	
	@Autowired
	ClienteEntityService clienteService;

	@Autowired
	CuentaEntityService cuentaService;
	
	@Autowired
	MovimientoEntityService movimientoService;
	
	
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
    		response.setMessage("Consulta exitosa de clientes!");
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
    		response.setMessage("Registro exitoso de cliente!");
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
    		response.setMessage("Eliminado exitoso de cliente!");
		} catch (Exception e) {
			response.setCode(EnumResponse.ERROR.code());
			response.setMessage(e.getMessage());
		}
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@GetMapping(value = "/cuentas/{idCliente}")
    public ResponseEntity<ResponseData<List<CuentaDto>>> getAccounts(@PathVariable("idCliente") final Integer idCliente) {
    	ResponseData<List<CuentaDto>> response = new ResponseData<>(EnumResponse.OK.code());
    	try {
    		List<CuentaEntity> pages = cuentaService.findByCliente(idCliente);
    		response.setData(pages.stream().map(r -> EntityDtoConverter.cuentaDto.apply(r)).collect(Collectors.toList()));
    		response.setMessage("Consulta exitosa de cuentas!");
		} catch (Exception e) {
			response.setCode(EnumResponse.ERROR.code());
			response.setMessage(e.getMessage());
		}
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@PostMapping(value = "/cuentas")
    public ResponseEntity<ResponseModel> postAccounts(@RequestBody CuentaDto request) {
    	ResponseModel response = new ResponseModel(EnumResponse.OK.code());
    	try {
    		cuentaService.saveOrUpdate(EntityDtoConverter.cuentaEntity.apply(request));
    		response.setMessage("Registro exitoso de cuenta!");
		} catch (Exception e) {
			response.setCode(EnumResponse.ERROR.code());
			response.setMessage(e.getMessage());
		}
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@DeleteMapping(value = "/cuentas")
    public ResponseEntity<ResponseModel> deleteAccounts(@RequestBody CuentaDto request) {
    	ResponseModel response = new ResponseModel(EnumResponse.OK.code());
    	try {
    		request.setStatus(EnumCatalog.INACTIVO.code());
    		cuentaService.saveOrUpdate(EntityDtoConverter.cuentaEntity.apply(request));
    		response.setMessage("Eliminado exitoso de cuenta!");
		} catch (Exception e) {
			response.setCode(EnumResponse.ERROR.code());
			response.setMessage(e.getMessage());
		}
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@GetMapping(value = "/movimientos/{idCuenta}/{size}")
    public ResponseEntity<ResponseData<List<MovimientoDto>>> getMoves(@PathVariable("idCuenta") final Integer idCuenta, @PathVariable("size") final Integer size) {
    	ResponseData<List<MovimientoDto>> response = new ResponseData<>(EnumResponse.OK.code());
    	try {
    		Page<MovimientoEntity> pages = movimientoService.findByCuenta(PageRequest.of(0, size, Sort.by("idMovimiento").descending()), idCuenta);
    		response.setData(pages.getContent().stream().map(r -> EntityDtoConverter.moveDto.apply(r)).collect(Collectors.toList()));
    		response.setMessage("Consulta exitosa de los ultimos movimientos!");
		} catch (Exception e) {
			response.setCode(EnumResponse.ERROR.code());
			response.setMessage(e.getMessage());
		}
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@PostMapping(value = "/movimientos/creditoDebito")
    public ResponseEntity<ResponseModel> credito(@RequestBody MovimientoDto request) {
    	ResponseModel response = new ResponseModel(EnumResponse.OK.code());
    	try {
    		if(request.getType().equals(EnumCatalog.CREDITO.code()))
    			movimientoService.credito(EntityDtoConverter.moveEntity.apply(request));
    		else
    			movimientoService.debito(EntityDtoConverter.moveEntity.apply(request));
    		response.setMessage(request.getType().equals(EnumCatalog.CREDITO.code()) ? "Credito":"Debito" + " exitoso de cuenta!");
		} catch (Exception e) {
			response.setCode(EnumResponse.ERROR.code());
			response.setMessage(e.getMessage());
		}
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@GetMapping(value = "/movimientos/reporte/{initDate}/{endDate}/{id}")
    public ResponseEntity<ResponseData<List<ReporteDto>>> getReport(@PathVariable("initDate") final String initDate, @PathVariable("endDate") final String endDate, @PathVariable("id") final String id) {
    	ResponseData<List<ReporteDto>> response = new ResponseData<>(EnumResponse.OK.code());
    	try {
    		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    		List<MovimientoEntity> pages = movimientoService.findByDateCliente(format.parse(initDate), format.parse(endDate), id);
    		response.setData(pages.stream().map(r -> EntityDtoConverter.reporteDto.apply(r)).collect(Collectors.toList()));
    		response.setMessage("Consulta exitosa de los ultimos movimientos!");
		} catch (Exception e) {
			response.setCode(EnumResponse.ERROR.code());
			response.setMessage(e.getMessage());
		}
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
