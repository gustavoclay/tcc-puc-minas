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

import br.com.geduca.api.model.Funcionario;
import br.com.geduca.api.service.FuncionarioService;

/**
 * @author gustavoclay
 *
 */
@RestController
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;

	@GetMapping
	public Page<Funcionario> pesquisar(@RequestParam int pagina, @RequestParam int max) {
		return funcionarioService.findAll(PageRequest.of(pagina, max));
	}

	@GetMapping(value = "lista")
	public List<Funcionario> pesquisar() {
		return funcionarioService.findAllList();
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Funcionario> buscaPeloCodigo(@PathVariable long codigo) {
		Funcionario funcionario = funcionarioService.buscaFuncionarioPeloCodigo(codigo);
		return funcionario != null ? ResponseEntity.ok(funcionario) : ResponseEntity.noContent().build();
	}

	@PostMapping
	public ResponseEntity<Funcionario> criar(@RequestBody Funcionario funcionario, HttpServletResponse response) {
		Funcionario funcionarioSalvo = funcionarioService.salvar(funcionario);
		return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioSalvo);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		funcionarioService.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Funcionario> atualizar(@PathVariable Long codigo,
			@Valid @RequestBody Funcionario funcionario) {
		Funcionario funcionarioSalvo = funcionarioService.atualizar(codigo, funcionario);
		return ResponseEntity.ok(funcionarioSalvo);
	}
}
