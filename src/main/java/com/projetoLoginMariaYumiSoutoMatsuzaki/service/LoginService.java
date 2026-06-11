package com.projetoLoginMariaYumiSoutoMatsuzaki.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.projetoLoginMariaYumiSoutoMatsuzaki.entity.Login;
import com.projetoLoginMariaYumiSoutoMatsuzaki.repository.LoginRepository;

@Service
public class LoginService {

	final private LoginRepository loginRepository;

	public LoginService(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}
	
	public Login authenticate(String username, String password) {
		Login user = loginRepository.findByUsername(username);
		
		if (user != null && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}

	public List<Login> buscarTodosLogin() {
		return loginRepository.findAll();
	}

	public Login buscarLoginPorId(Long id) {
		Optional<Login> login = loginRepository.findById(id);
		return login.orElse(null);
	}

	public Login salvarLogin(Login login) {
		return loginRepository.save(login);
	}

	public Login atualizarLogin(Long id, Login login) {
		Optional<Login> exeLogin = loginRepository.findById(id);
		if (exeLogin.isPresent()) {
			Login atLogin = exeLogin.get();
			BeanUtils.copyProperties(login, atLogin, "id");
			return loginRepository.save(atLogin);
		} 
		return null;
	}

	public Boolean apagarLogin(Long id) {
		Optional<Login> exeLogin = loginRepository.findById(id);
		if (exeLogin.isPresent()) {
			loginRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
