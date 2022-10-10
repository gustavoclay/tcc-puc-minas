package br.com.geduca.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.geduca.api.model.Unidade;
import br.com.geduca.api.repository.UnidadeRepository;

/**
 * @author gustavoclay
 *
 */
@Service
public class UnidadeService {
	
	@Autowired
	private UnidadeRepository unidadeRepository;

	public Unidade salvar(Unidade unidade) {
		return unidadeRepository.save(unidade);
	}

	public Unidade atualizar(Long codigo, Unidade unidade) {
		Unidade unidadeSalva = buscarUnidadePeloCodigo(codigo);
		BeanUtils.copyProperties(unidade, unidadeSalva, "codigo");
		return unidadeRepository.save(unidadeSalva);
	}

	public Unidade buscarUnidadePeloCodigo(Long codigo) {
		Unidade unidadeSalva = unidadeRepository.getOne(codigo);
		if (unidadeSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return unidadeSalva;
	}

	public Page<Unidade> findAll(Pageable pageable) {
		return unidadeRepository.findAll(pageable);
	}
	
	public List<Unidade> findAllList() {
		return unidadeRepository.findAll();
	}
	
	public Optional<Unidade> findById(long codigo) {
		return unidadeRepository.findById(codigo);
	}

	public void deleteById(Long codigo) {
		unidadeRepository.deleteById(codigo);
	}
	
}
