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

import br.com.geduca.api.model.Turma;
import br.com.geduca.api.service.TurmaService;

/**
 * @author gustavoclay
 *
 */
@RestController
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/turmas")
public class TurmaController {

	@Autowired
	private TurmaService turmaService;

	@GetMapping
	public Page<Turma> pesquisar(@RequestParam(required = false, defaultValue = "%") String nome,
			@RequestParam int pagina, @RequestParam int max) {
		return turmaService.findByNomeContaining(nome, PageRequest.of(pagina, max));
	}

	@GetMapping(value = "lista")
	public List<Turma> listarTodos() {
		return turmaService.findAllList();
	}

	@PostMapping
	public ResponseEntity<Turma> criar(@RequestBody Turma turma, HttpServletResponse response) {
		Turma turmaSalvo = turmaService.salvar(turma);
		return ResponseEntity.status(HttpStatus.CREATED).body(turmaSalvo);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Optional<Turma>> buscaPeloCodigo(@PathVariable Long codigo) {
		Optional<Turma> turma = turmaService.findById(codigo);
		if (turma != null)
			return ResponseEntity.ok(turma);
		else
			return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		turmaService.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Turma> atualizar(@PathVariable Long codigo, @RequestBody Turma turma) {
		Turma turmaSalvo = turmaService.atualizar(codigo, turma);
		return ResponseEntity.ok(turmaSalvo);
	}

}
