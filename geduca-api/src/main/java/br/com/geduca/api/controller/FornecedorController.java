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

import br.com.geduca.api.model.Fornecedor;
import br.com.geduca.api.model.RestricaoAlimentar;
import br.com.geduca.api.service.FornecedorService;

/**
 * @author gustavoclay
 *
 */
@RestController
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/fornecedores")
public class FornecedorController {

	@Autowired
	private FornecedorService fornecedorService;

	@GetMapping
	public Page<Fornecedor> pesquisar(@RequestParam int pagina, @RequestParam int max) {
		return fornecedorService.findAll(PageRequest.of(pagina, max));
	}
	
	@GetMapping(value = "lista")
	public List<Fornecedor> listarTodos() {
		return fornecedorService.findAllList();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Fornecedor> buscaPeloCodigo(@PathVariable long codigo) {
		Fornecedor fornecedor = fornecedorService.buscaFornecedorPeloCodigo(codigo);
		return fornecedor != null ? ResponseEntity.ok(fornecedor) : ResponseEntity.noContent().build();
	}

	@PostMapping
	public ResponseEntity<Fornecedor> criar(@RequestBody Fornecedor fornecedor, HttpServletResponse response) {
		Fornecedor fornecedorSalvo = fornecedorService.salvar(fornecedor);
		return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorSalvo);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		fornecedorService.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Fornecedor> atualizar(@PathVariable Long codigo, @Valid @RequestBody Fornecedor fornecedor) {
		Fornecedor fornecedorSalvo = fornecedorService.atualizar(codigo, fornecedor);
		return ResponseEntity.ok(fornecedorSalvo);
	}

}
