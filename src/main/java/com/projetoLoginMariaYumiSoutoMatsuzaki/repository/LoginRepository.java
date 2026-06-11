package com.projetoLoginMariaYumiSoutoMatsuzaki.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoLoginMariaYumiSoutoMatsuzaki.entity.Login;

public interface LoginRepository extends JpaRepository <Login, Long> {
	Login findByUsername(String username);
}
