package com.claro.mobile.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claro.mobile.api.entity.Mobile;
import com.claro.mobile.api.response.Response;
import com.claro.mobile.api.sequences.NextSequenceService;
import com.claro.mobile.api.services.MobileService;
import com.mongodb.DuplicateKeyException;

@RestController
@RequestMapping("/api/mobile")
@CrossOrigin(value="*")
public class MobileController {

	@Autowired
	private MobileService service;

	@Autowired
	private NextSequenceService nextSequenceService;
	
	@PostMapping()
	public ResponseEntity<Response<Mobile>> create(HttpServletRequest request, @RequestBody Mobile mobile,
			BindingResult result){
		Response<Mobile> response = new Response<>();
		try {
			validarMobile(mobile, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			
			Mobile existMobile = service.findById(mobile.getId());
			if (StringUtils.isEmpty(mobile.getId()) && existMobile == null) {
				String id = String.valueOf(nextSequenceService.getNextSequence("customSequences"));
				mobile.setId(id);
			}			
			Mobile mobilePersistido = service.createOrUpdate(mobile);
			response.setData(mobilePersistido);
		} catch (DuplicateKeyException de) {
			response.getErros().add("Model ou code ja registrado");
			return ResponseEntity.badRequest().body(response);
		} catch (Exception e) {
			response.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	@PutMapping
	public ResponseEntity<Response<Mobile>> update(HttpServletRequest request, @RequestBody Mobile mobile,
			BindingResult result){
		Response<Mobile> response = new Response<>();
		try {
			validarMobile(mobile, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			String id = String.valueOf(nextSequenceService.getNextSequence("customSequences"));
			mobile.setId(id);
			Mobile mobilePersistido = service.createOrUpdate(mobile);
			response.setData(mobilePersistido);
		} catch (DuplicateKeyException de) {
			response.getErros().add("Model ou code ja registrado");
			return ResponseEntity.badRequest().body(response);
		} catch (Exception e) {
			response.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	private void validarMobile(Mobile mobile, BindingResult result) {
		
		if (StringUtils.isEmpty(mobile.getModel())) {
			result.addError(new ObjectError("mobile", "model não informado"));
		}
		
		if (StringUtils.isEmpty(mobile.getCode())) {
			result.addError(new ObjectError("mobile", "code não informado"));
		}
	}
	
	@GetMapping(value="{id}")
	public ResponseEntity<Response<Mobile>> findById(@PathVariable("id") String id){
		Response<Mobile> response = new Response<Mobile>();
		Mobile mobile = service.findById(id);
		if (mobile == null) {
			response.getErros().add("Registro não encontrado id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(mobile);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value="{id}")
	public ResponseEntity<Response<Mobile>> delete(@PathVariable("id") String id){
		Response<Mobile> response = new Response<Mobile>();
		Mobile mobile = service.findById(id);
		if (mobile == null) {
			response.getErros().add("Registro não encontrado id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		service.delete(id);
		return ResponseEntity.ok(new Response<>());
	}
	
	@GetMapping(value = "{page}/{count}")
	public ResponseEntity<Response<Page<Mobile>>> findAll(@PathVariable int page, @PathVariable int count){
		Response<Page<Mobile>> response = new Response<Page<Mobile>>();
		Page<Mobile> mobiles = service.findAll(page, count);
		response.setData(mobiles);
		return ResponseEntity.ok(response);
	}
}
