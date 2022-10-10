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

import br.com.geduca.api.model.Curso;
import br.com.geduca.api.service.CursoService;

/**
 * @author gustavoclay
 *
 */
@RestController
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/cursos")
public class CursoController {

	@Autowired
	private CursoService cursoService;

	@GetMapping
	public Page<Curso> pesquisar(@RequestParam(required = false, defaultValue = "%") String nome,
			@RequestParam int pagina, @RequestParam int max) {
		return cursoService.findByNomeContaining(nome, PageRequest.of(pagina, max));
	}

	@GetMapping(value = "lista")
	public List<Curso> listarTodos() {
		return cursoService.findAllList();
	}

	@PostMapping
	public ResponseEntity<Curso> criar(@RequestBody Curso curso, HttpServletResponse response) {
		Curso cursoSalvo = cursoService.salvar(curso);
		return ResponseEntity.status(HttpStatus.CREATED).body(cursoSalvo);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Optional<Curso>> buscaPeloCodigo(@PathVariable Long codigo) {
		Optional<Curso> curso = cursoService.findById(codigo);
		if (curso != null)
			return ResponseEntity.ok(curso);
		else
			return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		cursoService.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Curso> atualizar(@PathVariable Long codigo, @RequestBody Curso curso) {
		Curso cursoSalvo = cursoService.atualizar(codigo, curso);
		return ResponseEntity.ok(cursoSalvo);
	}

}
