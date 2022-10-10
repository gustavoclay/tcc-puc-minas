package br.com.geduca.api.controller;

import java.util.List;

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
import br.com.geduca.api.model.dao.EstoqueDAO;
import br.com.geduca.api.service.EstoqueService;

/**
 * @author gustavoclay
 *
 */
@RestController
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/estoque")
public class EstoqueController {
	@Autowired
	private EstoqueService estoqueService;

	@GetMapping(value = "todos")
	public List<Estoque> listaTodos() {
		return estoqueService.listaTodos();
	}

	@GetMapping
	public Page<Estoque> lista(@RequestParam int pagina, @RequestParam int max) {
		return estoqueService.lista(PageRequest.of(pagina, max));
	}
	
	@GetMapping(value = "despensa")
	public Page<EstoqueDAO> buscaPorDespensa(@RequestParam int pagina, @RequestParam int max, @RequestParam Long codigoDespensa) {
		return estoqueService.listaPorDespensa(codigoDespensa, PageRequest.of(pagina, max));
	}

	@PostMapping
	public Estoque criar(@RequestBody Estoque estoque) {
		return estoqueService.save(estoque);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Estoque> atualizar(@PathVariable Long codigo, @RequestBody Estoque estoque) {
		Estoque estoqueSalvo = estoqueService.atualizar(codigo, estoque);
		return ResponseEntity.ok(estoqueSalvo);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		estoqueService.deleteById(codigo);
	}

}
