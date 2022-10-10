package br.com.geduca.api.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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

import br.com.geduca.api.model.Receita;
import br.com.geduca.api.service.ReceitaService;
import event.RecursoCriadoEvent;

/**
 * @author gustavoclay
 *
 */
@RestController
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/receitas")
public class ReceitaController {

	@Autowired
	private ReceitaService receitaService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public Page<Receita> pesquisar(@RequestParam int pagina, @RequestParam int max) {
		return receitaService.findAll(PageRequest.of(pagina, max));
	}

	@GetMapping(value = "lista")
	public List<Receita> listarTodos() {
		return receitaService.findAllList();
	}

	@PostMapping
	public ResponseEntity<Receita> criar(@RequestBody Receita receita, HttpServletResponse response) {
		Receita receitaSalva = receitaService.salvar(receita);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, receitaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(receitaSalva);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Optional<Receita>> buscaPeloCodigo(@PathVariable Long codigo) {
		Optional<Receita> receita = receitaService.findById(codigo);
		if (receita != null)
			return ResponseEntity.ok(receita);
		else
			return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		receitaService.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Receita> atualizar(@PathVariable Long codigo, @RequestBody Receita receita) {
		Receita receitaSalva = receitaService.atualizar(codigo, receita);
		return ResponseEntity.ok(receitaSalva);
	}

}
