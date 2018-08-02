package com.claro.mobile.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claro.mobile.api.entity.User;
import com.claro.mobile.api.response.Response;
import com.claro.mobile.api.services.UserService;
import com.mongodb.DuplicateKeyException;

@RestController
@RequestMapping("/api/user")
//@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserService service;
	
	/*@Autowired
	private PasswordEncoder passwordEncoder;*/
	
	@PostMapping
	//@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<User>> create(HttpServletRequest request, @RequestBody User user,
			BindingResult result){
		Response<User> response = new Response<User>();
		try {
			validarUser(user, result);
			
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			
			//user.setPassword(passwordEncoder.encode(user.getPassword()));
			User userPersistido = service.createOrUpdate(user);
			response.setData(userPersistido);
			
		} catch (DuplicateKeyException de) {
			response.getErros().add("email ja registrado");
			return ResponseEntity.badRequest().body(response);
			
		} catch (Exception e) {
			response.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	private void validarUser(User user, BindingResult result) {
		if (StringUtils.isEmpty(user.getEmail())) {
			result.addError(new ObjectError("user", "email não informado"));
		}
	}
}
