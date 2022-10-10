package br.com.geduca.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.geduca.api.model.Aluno;
import br.com.geduca.api.model.TurmaAluno;
import br.com.geduca.api.service.TurmaAlunoService;

/**
 * @author gustavoclay
 *
 */
@RestController
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/turmas_alunos")
public class TurmaAlunoController {

	@Autowired
	private TurmaAlunoService turmaAlunoService;

	@GetMapping(value = "todos")
	public List<TurmaAluno> listaTodos() {
		return turmaAlunoService.listaTodos();
	}

	@GetMapping(value = "turma")
	public List<TurmaAluno> buscaPorTurma(@RequestParam Long codigoTurma) {
		return turmaAlunoService.getByTurma(codigoTurma);
	}

	@GetMapping(value = "aluno")
	public List<TurmaAluno> buscaPorAluno(@RequestParam Long codigoAluno) {
		return turmaAlunoService.getByAluno(codigoAluno);
	}

	@PostMapping
	public ResponseEntity<Object> criar(@RequestParam Long codigoTurma, @RequestBody List<Aluno> alunos) {
		return turmaAlunoService.save(codigoTurma, alunos);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		turmaAlunoService.deleteById(codigo);
	}

}
