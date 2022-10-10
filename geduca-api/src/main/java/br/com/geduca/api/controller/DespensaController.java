package br.com.geduca.api.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

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

import br.com.geduca.api.model.Despensa;
import br.com.geduca.api.service.DespensaService;

/**
 * @author gustavoclay
 *
 */
@RestController
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/despensa")
public class DespensaController {

	@Autowired
	private DespensaService despensaService;

	@GetMapping
	public Page<Despensa> pesquisar(@RequestParam(required = false, defaultValue = "%") String nome,
			@RequestParam int pagina, @RequestParam int max) {
		return despensaService.findByNomeContaining(nome, PageRequest.of(pagina, max));
	}

	@GetMapping(value = "lista")
	public List<Despensa> listarTodos() {
		return despensaService.findAllList();
	}

	@PostMapping
	public ResponseEntity<Despensa> criar(@RequestBody Despensa despensa, HttpServletResponse response) {
		Despensa dispensaSalvo = despensaService.salvar(despensa);
		return ResponseEntity.status(HttpStatus.CREATED).body(dispensaSalvo);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Optional<Despensa>> buscaPeloCodigo(@PathVariable Long codigo) {
		Optional<Despensa> despensa = despensaService.findById(codigo);
		if (despensa != null)
			return ResponseEntity.ok(despensa);
		else
			return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		despensaService.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Despensa> atualizar(@PathVariable Long codigo, @RequestBody Despensa despensa) {
		Despensa despensaSalvo = despensaService.atualizar(codigo, despensa);
		return ResponseEntity.ok(despensaSalvo);
	}

}
