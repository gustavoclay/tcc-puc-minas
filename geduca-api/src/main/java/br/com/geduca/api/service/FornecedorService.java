package br.com.geduca.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.geduca.api.model.Fornecedor;
import br.com.geduca.api.model.RestricaoAlimentar;
import br.com.geduca.api.repository.FornecedorRepository;

/**
 * @author gustavoclay
 *
 */
@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository fornecedorRepository;

	public Fornecedor salvar(Fornecedor fornecedor) {
		return fornecedorRepository.save(fornecedor);
	}

	public Fornecedor atualizar(Long codigo, Fornecedor fornecedor) {
		Fornecedor fornecedorSalvo = fornecedorRepository.findById(codigo).get();
		fornecedorSalvo = fornecedor;
		return fornecedorRepository.save(fornecedorSalvo);
	}

	public Fornecedor buscaFornecedorPeloCodigo(Long codigo) {
		Fornecedor fornecedorSalvo = fornecedorRepository.getOne(codigo);
		if (fornecedorSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return fornecedorSalvo;
	}

	public Page<Fornecedor> findAll(Pageable pageable) {
		return fornecedorRepository.findAll(pageable);
	}
	
	public List<Fornecedor> findAllList() {
		return fornecedorRepository.findAll();
	}

	public Optional<Fornecedor> findById(long codigo) {
		return fornecedorRepository.findById(codigo);
	}

	public void deleteById(Long codigo) {
		fornecedorRepository.deleteById(codigo);
	}

}
