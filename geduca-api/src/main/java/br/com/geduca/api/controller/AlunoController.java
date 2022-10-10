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

import br.com.geduca.api.model.Aluno;
import br.com.geduca.api.service.AlunoService;

/**
 * @author gustavoclay
 *
 */
@RestController
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/alunos")
public class AlunoController {

	@Autowired
	private AlunoService alunoService;

	@GetMapping
	public Page<Aluno> pesquisar(@RequestParam int pagina, @RequestParam int max) {
		return alunoService.findAll(PageRequest.of(pagina, max));
	}

	@GetMapping(value = "lista")
	public List<Aluno> pesquisar() {
		return alunoService.findAllList();
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Aluno> buscaPeloCodigo(@PathVariable long codigo) {
		Aluno aluno = alunoService.buscaAlunoPeloCodigo(codigo);
		return aluno != null ? ResponseEntity.ok(aluno) : ResponseEntity.noContent().build();
	}

	@PostMapping
	public ResponseEntity<Aluno> criar(@RequestBody Aluno aluno, HttpServletResponse response) {
		Aluno alunoSalvo = alunoService.salvar(aluno);
		return ResponseEntity.status(HttpStatus.CREATED).body(alunoSalvo);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		alunoService.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Aluno> atualizar(@PathVariable Long codigo, @Valid @RequestBody Aluno aluno) {
		Aluno alunoSalvo = alunoService.atualizar(codigo, aluno);
		return ResponseEntity.ok(alunoSalvo);
	}

}
