package br.com.geduca.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.geduca.api.model.Receita;
import br.com.geduca.api.repository.ReceitaRepository;

/**
 * @author gustavoclay
 *
 */
@Service
public class ReceitaService {
	
	@Autowired
	private ReceitaRepository receitaRepository;

	public Receita salvar(Receita receita) {
		return receitaRepository.save(receita);
	}

	public Receita atualizar(Long codigo, Receita receita) {
		Receita receitaSalva = buscarReceitaPeloCodigo(codigo);
		BeanUtils.copyProperties(receita, receitaSalva, "codigo");
		return receitaRepository.save(receitaSalva);
	}

	public Receita buscarReceitaPeloCodigo(Long codigo) {
		Receita receitaSalva = receitaRepository.getOne(codigo);
		if (receitaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return receitaSalva;
	}

	public Page<Receita> findAll(Pageable pageable) {
		return receitaRepository.findAll(pageable);
	}
	
	public List<Receita> findAllList() {
		return receitaRepository.findAll();
	}
	
	public Optional<Receita> findById(long codigo) {
		return receitaRepository.findById(codigo);
	}

	public void deleteById(Long codigo) {
		receitaRepository.deleteById(codigo);
	}

}
