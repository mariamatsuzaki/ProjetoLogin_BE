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

import com.projetoLoginMariaYumiSoutoMatsuzaki.entity.Estoque;
import com.projetoLoginMariaYumiSoutoMatsuzaki.service.EstoqueService;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
	private final EstoqueService estoqueService;

	public EstoqueController(EstoqueService estoqueService) {
		this.estoqueService = estoqueService;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Estoque>> findAllEstoques() {
		List<Estoque> estoque = estoqueService.findAllEstoques();
		return ResponseEntity.ok(estoque);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Estoque> findEstoqueById(@PathVariable Long id) {
		Estoque estoque = estoqueService.findEstoqueById(id);
		if (estoque != null) {
			return ResponseEntity.ok(estoque);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<Estoque> saveEstoque(@RequestBody Estoque estoque) {
		Estoque saveEstoque = estoqueService.saveEstoque(estoque);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveEstoque);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Estoque> updateEstoque(@RequestBody Estoque estoque, @PathVariable Long id) {
		Estoque updEstoque = estoqueService.updateEstoque(estoque, id);
		if (updEstoque != null) {
			return ResponseEntity.ok(updEstoque);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Estoque> deleteEstoque(@PathVariable Long id) {
		boolean delEstoque = estoqueService.deleteEstoque(id);
		if (delEstoque) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
