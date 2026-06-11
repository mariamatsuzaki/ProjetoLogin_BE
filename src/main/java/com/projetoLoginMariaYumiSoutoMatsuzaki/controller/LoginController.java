package com.projetoLoginMariaYumiSoutoMatsuzaki.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoLoginMariaYumiSoutoMatsuzaki.entity.Login;
import com.projetoLoginMariaYumiSoutoMatsuzaki.service.LoginService;

@RestController
@RequestMapping("/users")
public class LoginController {

	private final LoginService loginService;

	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	@GetMapping("/")
	public ResponseEntity<List<Login>> buscarLogins() {
		List<Login> logins = loginService.buscarTodosLogin();
		return ResponseEntity.ok(logins);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Login> buscarLoginId(@PathVariable Long id) {
		Login login = loginService.buscarLoginPorId(id);
		if (login != null) {
			return ResponseEntity.ok(login);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/")
	public ResponseEntity<Login> salvaLogin(@RequestBody Login login) {
		Login savelogin = loginService.salvarLogin(login);
		return ResponseEntity.status(HttpStatus.CREATED).body(savelogin);
	}
	
    @PostMapping("/auth")
    public ResponseEntity<Login> authenticate(@RequestBody Login loginDetails){
    	Login authenticatedUser = loginService.authenticate(loginDetails.getUsername(), loginDetails.getPassword());
    	if (authenticatedUser != null) {
    		authenticatedUser.setPassword(null);
    		return ResponseEntity.ok(authenticatedUser);
    	}
    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    
	@PutMapping("/{id}")
	public ResponseEntity<Login> alteraLogin(@PathVariable Long id, @RequestBody Login login) {
		Login atualizalogin = loginService.atualizarLogin(id, login);
		if (atualizalogin != null) {
			return ResponseEntity.ok(atualizalogin);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Login> apagalogin(@PathVariable Long id) {
		boolean apagalogin = loginService.apagarLogin(id);
		if (apagalogin) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
