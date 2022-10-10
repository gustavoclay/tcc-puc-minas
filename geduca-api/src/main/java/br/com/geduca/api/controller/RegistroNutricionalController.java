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

import br.com.geduca.api.model.RegistroNutricional;
import br.com.geduca.api.service.RegistroNutricionalService;

/**
 * @author gustavoclay
 *
 */
@RestController
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/registros-nutricionais")
public class RegistroNutricionalController {

	@Autowired
	private RegistroNutricionalService registroNutricionalService;

	@GetMapping
	public Page<RegistroNutricional> pesquisarPorAluno(@RequestParam Long codigoAluno, @RequestParam int pagina,
			@RequestParam int max) {
		return registroNutricionalService.findAllByAluno(codigoAluno, PageRequest.of(pagina, max));
	}

	@PostMapping
	public ResponseEntity<RegistroNutricional> criar(@RequestBody RegistroNutricional registroNutricional,
			HttpServletResponse response) {
		RegistroNutricional registroNutricionalSalvo = registroNutricionalService.salvar(registroNutricional);
		return ResponseEntity.status(HttpStatus.CREATED).body(registroNutricionalSalvo);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Optional<RegistroNutricional>> buscaPeloCodigo(@PathVariable Long codigo) {
		Optional<RegistroNutricional> registroNutricional = registroNutricionalService.findById(codigo);
		if (registroNutricional != null)
			return ResponseEntity.ok(registroNutricional);
		else
			return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		registroNutricionalService.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<RegistroNutricional> atualizar(@PathVariable Long codigo,
			@RequestBody RegistroNutricional registroNutricional) {
		RegistroNutricional registroNutricionalSalvo = registroNutricionalService.atualizar(codigo,
				registroNutricional);
		return ResponseEntity.ok(registroNutricionalSalvo);
	}

}
