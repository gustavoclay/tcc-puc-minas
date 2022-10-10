package br.com.geduca.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.geduca.api.model.Curso;
import br.com.geduca.api.model.RestricaoAlimentar;
import br.com.geduca.api.repository.CursoRepository;

/**
 * 
 * @author gustavoclay
 * 
 */
@Service
public class CursoService {

	@Autowired
	private CursoRepository cursoRepository;

	public Curso salvar(Curso curso) {
		curso.setAtivo(true);
		return cursoRepository.save(curso);
	}

	public Curso atualizar(Long codigo, Curso curso) {
		Curso cursoSalvo = buscarCursoPeloCodigo(codigo);
		BeanUtils.copyProperties(curso, cursoSalvo, "codigo");
		return cursoRepository.save(cursoSalvo);
	}

	public Curso buscarCursoPeloCodigo(Long codigo) {
		Curso cursoSalvo = cursoRepository.getOne(codigo);
		if (cursoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return cursoSalvo;
	}

	public Page<Curso> findByNomeContaining(String nome, Pageable paginacao) {
		return cursoRepository.findByNomeContaining(nome, paginacao);
	}
	
	public List<Curso> findAllList() {
		return cursoRepository.findAll();
	}

	public Optional<Curso> findById(Long codigo) {
		return cursoRepository.findById(codigo);
	}

	public void deleteById(Long codigo) {
		cursoRepository.deleteById(codigo);
	}
}
