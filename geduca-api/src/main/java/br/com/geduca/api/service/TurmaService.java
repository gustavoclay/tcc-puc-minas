package br.com.geduca.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.geduca.api.model.Turma;
import br.com.geduca.api.repository.TurmaRepository;

/**
 * @author gustavoclay
 *
 */
@Service
public class TurmaService {

	@Autowired
	private TurmaRepository turmaRepository;

	public Turma salvar(Turma turma) {
		turma.setAtivo(true);
		return turmaRepository.save(turma);
	}

	public Turma atualizar(Long codigo, Turma turma) {
		Turma turmaSalvo = buscarTurmaPeloCodigo(codigo);
		BeanUtils.copyProperties(turma, turmaSalvo, "codigo");
		return turmaRepository.save(turmaSalvo);
	}

	public Turma buscarTurmaPeloCodigo(Long codigo) {
		Turma turmaSalvo = turmaRepository.getOne(codigo);
		if (turmaSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return turmaSalvo;
	}

	public Page<Turma> findByNomeContaining(String nome, Pageable paginacao) {
		return turmaRepository.findByNomeContaining(nome, paginacao);
	}

	public List<Turma> findAllList() {
		return turmaRepository.findAll();
	}

	public Optional<Turma> findById(Long codigo) {
		return turmaRepository.findById(codigo);
	}

	public void deleteById(Long codigo) {
		turmaRepository.deleteById(codigo);
	}

}
