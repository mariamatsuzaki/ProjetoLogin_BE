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

import com.projetoLoginMariaYumiSoutoMatsuzaki.entity.Produtos;
import com.projetoLoginMariaYumiSoutoMatsuzaki.service.ProdutosService;

@RestController
	@RequestMapping("/produtos")
public class ProdutosController {
	
		private final ProdutosService produtosService;

		public ProdutosController(ProdutosService produtosService) {
			this.produtosService = produtosService;
		}

		@GetMapping("/")
		public ResponseEntity<List<Produtos>> buscarProdutoss() {
			List<Produtos> produtoss = produtosService.buscarTodosProdutos();
			return ResponseEntity.ok(produtoss);
		}

		@GetMapping("/{id}")
		public ResponseEntity<Produtos> buscarProdutosId(@PathVariable Long id) {
			Produtos produtos = produtosService.buscarProdutosPorId(id);
			if (produtos != null) {
				return ResponseEntity.ok(produtos);
			} else {
				return ResponseEntity.notFound().build();
			}
		}

		@PostMapping("/")
		public ResponseEntity<Produtos> salvaProdutos(@RequestBody Produtos produtos) {
			Produtos saveprodutos = produtosService.salvarProdutos(produtos);
			return ResponseEntity.status(HttpStatus.CREATED).body(saveprodutos);
		}

		@PutMapping("/{id}")
		public ResponseEntity<Produtos> alteraProdutos(@PathVariable Long id, @RequestBody Produtos produtos) {
			Produtos atualizaprodutos = produtosService.atualizarProdutos(id, produtos);
			if (atualizaprodutos != null) {
				return ResponseEntity.ok(atualizaprodutos);
			} else {
				return ResponseEntity.notFound().build();
			}
		}

		@DeleteMapping("/{id}")
		public ResponseEntity<Produtos> apagaprodutos(@PathVariable Long id) {
			boolean apagaprodutos = produtosService.apagarProdutos(id);
			if (apagaprodutos) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			} else {
				return ResponseEntity.notFound().build();
			}
		}
	}


