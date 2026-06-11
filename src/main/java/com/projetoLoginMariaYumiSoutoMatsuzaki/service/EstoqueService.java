package com.projetoLoginMariaYumiSoutoMatsuzaki.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.projetoLoginMariaYumiSoutoMatsuzaki.entity.Estoque;
import com.projetoLoginMariaYumiSoutoMatsuzaki.repository.EstoqueRepository;

@Service
public class EstoqueService {
	private final EstoqueRepository estoqueRepository;

	public EstoqueService(EstoqueRepository estoqueRepository) {
		this.estoqueRepository = estoqueRepository;
	}
	
	public List<Estoque> findAllEstoques() {
		return estoqueRepository.findAll();
	}
	
	public Estoque findEstoqueById(Long id) {
		Optional<Estoque> existEstoque = estoqueRepository.findById(id);
		return existEstoque.orElse(null);
	}
	
	public Estoque saveEstoque(Estoque estoque) {
		return estoqueRepository.save(estoque);
	}
	
	public Estoque updateEstoque(Estoque estoque, Long id) {
		Optional<Estoque> existEstoque = estoqueRepository.findById(id);
		if (existEstoque.isPresent()) {
			Estoque updEstoque = existEstoque.get();
			BeanUtils.copyProperties(estoque, updEstoque, "id");
			return estoqueRepository.save(updEstoque);
		}
		return null;
	}
	
	public boolean deleteEstoque(Long id) {
		Optional<Estoque> existEstoque = estoqueRepository.findById(id);
		if (existEstoque.isPresent()) {
			estoqueRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
