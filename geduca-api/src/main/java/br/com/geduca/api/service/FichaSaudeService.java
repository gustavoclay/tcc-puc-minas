package br.com.geduca.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.geduca.api.model.FichaSaude;
import br.com.geduca.api.repository.FichaSaudeRepository;

/**
 * @author gustavoclay
 *
 */
@Service
public class FichaSaudeService {

	@Autowired
	private FichaSaudeRepository fichaSaudeRepository;

	public FichaSaude salvar(FichaSaude FichaSaude) {
		return fichaSaudeRepository.save(FichaSaude);
	}

	public FichaSaude buscarFichaSaudePeloCodigo(Long codigo) {
		FichaSaude FichaSaudeSalva = fichaSaudeRepository.getOne(codigo);
		if (FichaSaudeSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return FichaSaudeSalva;
	}

	public Page<FichaSaude> findAll(Pageable pageable) {
		return fichaSaudeRepository.findAll(pageable);
	}

	public Optional<FichaSaude> findById(long codigo) {
		return fichaSaudeRepository.findById(codigo);
	}

	public void deleteById(Long codigo) {
		fichaSaudeRepository.deleteById(codigo);
	}

}
