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

import br.com.geduca.api.model.RestricaoAlimentar;
import br.com.geduca.api.service.RestricaoAlimentarService;
import event.RecursoCriadoEvent;

/**
 * @author gustavoclay
 *
 */
@RestController
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/restricoes-alimentares")
public class RestricaoAlimentarController {

	@Autowired
	private RestricaoAlimentarService restricaoAlimentarService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public Page<RestricaoAlimentar> pesquisar(@RequestParam int pagina, @RequestParam int max) {
		return restricaoAlimentarService.findAll(PageRequest.of(pagina, max));
	}

	@GetMapping(value = "lista")
	public List<RestricaoAlimentar> listarTodos() {
		return restricaoAlimentarService.findAllList();
	}

	@PostMapping
	public ResponseEntity<RestricaoAlimentar> criar(@RequestBody RestricaoAlimentar restricaoAlimentar,
			HttpServletResponse response) {
		RestricaoAlimentar restricaoAlimentarSalva = restricaoAlimentarService.salvar(restricaoAlimentar);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, restricaoAlimentarSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(restricaoAlimentarSalva);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Optional<RestricaoAlimentar>> buscaPeloCodigo(@PathVariable Long codigo) {
		Optional<RestricaoAlimentar> restricaoAlimentar = restricaoAlimentarService.findById(codigo);
		if (restricaoAlimentar != null)
			return ResponseEntity.ok(restricaoAlimentar);
		else
			return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		restricaoAlimentarService.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<RestricaoAlimentar> atualizar(@PathVariable Long codigo,
			@RequestBody RestricaoAlimentar restricaoAlimentar) {
		RestricaoAlimentar restricaoAlimentarSalva = restricaoAlimentarService.atualizar(codigo, restricaoAlimentar);
		return ResponseEntity.ok(restricaoAlimentarSalva);
	}

}
