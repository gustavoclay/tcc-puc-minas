package br.com.geduca.api.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.geduca.api.model.Pessoa;
import br.com.geduca.api.repository.PessoaRepository;

/**
 * @author gustavoclay
 *
 */
@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa salvar(Pessoa pessoa) {
		pessoa.setAtivo(true);
		return pessoaRepository.save(pessoa);
	}

	public Pessoa atualizar(Long codigo, Pessoa pessoa) {
		Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);
		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
		return pessoaRepository.save(pessoaSalva);
	}

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);
		pessoaSalva.setAtivo(ativo);
		pessoaRepository.save(pessoaSalva);
	}

	public Pessoa buscarPessoaPeloCodigo(Long codigo) {
		Pessoa pessoaSalva = pessoaRepository.getOne(codigo);
		if (pessoaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pessoaSalva;
	}

	public Page<Pessoa> findByNomeContaining(String nome, Pageable paginacao) {
		return pessoaRepository.findByNomeContaining(nome, paginacao);
	}

	public Optional<Pessoa> findById(long codigo) {
		return pessoaRepository.findById(codigo);
	}

	public void deleteById(Long codigo) {
		pessoaRepository.deleteById(codigo);
	}
	
	public int calculaIdade(Pessoa pessoa) {
		Period periodo = Period.between(pessoa.getDataNascimento(), LocalDate.now());
		return periodo.getYears();
	}
}
