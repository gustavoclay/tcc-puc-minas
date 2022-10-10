package br.com.geduca.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.geduca.api.model.Despensa;
import br.com.geduca.api.repository.DespensaRepository;

/**
 * 
 * @author gustavoclay
 * 
 */
@Service
public class DespensaService {

	@Autowired
	private DespensaRepository despensaRepository;

	public Despensa salvar(Despensa despensa) {
		return despensaRepository.save(despensa);
	}

	public Despensa atualizar(Long codigo, Despensa despensa) {
		Despensa despensaSalvo = buscarDispensaPeloCodigo(codigo);
		BeanUtils.copyProperties(despensa, despensaSalvo, "codigo");
		return despensaRepository.save(despensaSalvo);
	}

	public Despensa buscarDispensaPeloCodigo(Long codigo) {
		Despensa despensaSalvo = despensaRepository.getOne(codigo);
		if (despensaSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return despensaSalvo;
	}

	public Page<Despensa> findByNomeContaining(String nome, Pageable paginacao) {
		return despensaRepository.findByNomeContaining(nome, paginacao);
	}
	
	public List<Despensa> findAllList() {
		return despensaRepository.findAll();
	}

	public Optional<Despensa> findById(Long codigo) {
		return despensaRepository.findById(codigo);
	}

	public void deleteById(Long codigo) {
		despensaRepository.deleteById(codigo);
	}
}
