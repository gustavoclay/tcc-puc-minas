package br.com.geduca.api.controller;

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

import br.com.geduca.api.model.RegistroAlimentar;
import br.com.geduca.api.service.RegistroAlimentarService;

/**
 * @author gustavoclay
 *
 */
@RestController
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/registros-alimentares")
public class RegistroAlimentarController {

	@Autowired
	private RegistroAlimentarService registroAlimentarService;

	@GetMapping
	public Page<RegistroAlimentar> pesquisarPorAluno(@RequestParam Long codigoAluno, @RequestParam int pagina,
			@RequestParam int max) {
		return registroAlimentarService.findAllByAluno(codigoAluno, PageRequest.of(pagina, max));
	}

	@PostMapping
	public ResponseEntity<RegistroAlimentar> criar(@RequestBody RegistroAlimentar registroAlimentar,
			HttpServletResponse response) {
		RegistroAlimentar registroAlimentarSalvo = registroAlimentarService.salvar(registroAlimentar);
		return ResponseEntity.status(HttpStatus.CREATED).body(registroAlimentarSalvo);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Optional<RegistroAlimentar>> buscaPeloCodigo(@PathVariable Long codigo) {
		Optional<RegistroAlimentar> registroAlimentar = registroAlimentarService.findById(codigo);
		if (registroAlimentar != null)
			return ResponseEntity.ok(registroAlimentar);
		else
			return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		registroAlimentarService.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<RegistroAlimentar> atualizar(@PathVariable Long codigo,
			@RequestBody RegistroAlimentar registroAlimentar) {
		RegistroAlimentar registroAlimentarSalvo = registroAlimentarService.atualizar(codigo, registroAlimentar);
		return ResponseEntity.ok(registroAlimentarSalvo);
	}

}
