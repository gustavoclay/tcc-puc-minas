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

import br.com.geduca.api.model.Unidade;
import br.com.geduca.api.service.UnidadeService;
import event.RecursoCriadoEvent;

/**
 * @author gustavoclay
 *
 */
@RestController
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/unidades")
public class UnidadeController {

	@Autowired
	private UnidadeService unidadeService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public Page<Unidade> pesquisar(@RequestParam int pagina, @RequestParam int max) {
		return unidadeService.findAll(PageRequest.of(pagina, max));
	}

	@GetMapping(value = "lista")
	public List<Unidade> listarTodos() {
		return unidadeService.findAllList();
	}

	@PostMapping
	public ResponseEntity<Unidade> criar(@RequestBody Unidade unidade,
			HttpServletResponse response) {
		Unidade unidadeSalva = unidadeService.salvar(unidade);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, unidadeSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(unidadeSalva);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Optional<Unidade>> buscaPeloCodigo(@PathVariable Long codigo) {
		Optional<Unidade> unidade = unidadeService.findById(codigo);
		if (unidade != null)
			return ResponseEntity.ok(unidade);
		else
			return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		unidadeService.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Unidade> atualizar(@PathVariable Long codigo,
			@RequestBody Unidade unidade) {
		Unidade unidadeSalva = unidadeService.atualizar(codigo, unidade);
		return ResponseEntity.ok(unidadeSalva);
	}
}
