	package br.com.geduca.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.geduca.api.model.Estoque;
import br.com.geduca.api.model.Produto;
import br.com.geduca.api.service.ProdutoService;

/**
 * @author gustavoclay
 *
 */
@RestController
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping(value = "lista")
	public List<Produto> listaTodos() {
		return produtoService.findAllList();
	}

	@GetMapping
	public Page<Produto> pesquisar(@RequestParam int pagina, @RequestParam int max) {
		return produtoService.findAll(PageRequest.of(pagina, max));
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Produto> buscaPeloCodigo(@PathVariable long codigo) {
		Produto produto = produtoService.buscaProdutoPeloCodigo(codigo);
		return produto != null ? ResponseEntity.ok(produto) : ResponseEntity.noContent().build();
	}

	@PostMapping
	public ResponseEntity<Produto> criar(@RequestBody Produto produto, HttpServletResponse response) {
		Produto produtoSalvo = produtoService.salvar(produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		produtoService.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Produto> atualizar(@PathVariable Long codigo, @Valid @RequestBody Produto produto) {
		Produto produtoSalvo = produtoService.atualizar(codigo, produto);
		return ResponseEntity.ok(produtoSalvo);
	}

}
