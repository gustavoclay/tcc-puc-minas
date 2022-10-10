package br.com.geduca.api.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.geduca.api.model.Aluno;
import br.com.geduca.api.model.RegistroAlimentar;
import br.com.geduca.api.repository.RegistroAlimentarRepository;

/**
 * 
 * @author gustavoclay
 * 
 */
@Service
public class RegistroAlimentarService {

	@Autowired
	private RegistroAlimentarRepository registroAlimentarRepository;

	@Autowired
	private AlunoService alunoService;

	public RegistroAlimentar salvar(RegistroAlimentar registroAlimentar) {
		registroAlimentar.setDataRegistro(LocalDate.now());
		return registroAlimentarRepository.save(registroAlimentar);
	}

	public RegistroAlimentar atualizar(Long codigo, RegistroAlimentar registroAlimentar) {
		RegistroAlimentar registroAlimentarSalvo = buscarRegistroAlimentarPeloCodigo(codigo);
		BeanUtils.copyProperties(registroAlimentar, registroAlimentarSalvo, "codigo");
		registroAlimentarSalvo.setDataRegistro(LocalDate.now());
		return registroAlimentarRepository.save(registroAlimentarSalvo);
	}

	public RegistroAlimentar buscarRegistroAlimentarPeloCodigo(Long codigo) {
		RegistroAlimentar registroAlimentarSalvo = registroAlimentarRepository.getOne(codigo);
		if (registroAlimentarSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return registroAlimentarSalvo;
	}

	public List<RegistroAlimentar> findAllList() {
		return registroAlimentarRepository.findAll();
	}

	public Page<RegistroAlimentar> findAllByAluno(Long codigoAluno, Pageable pageable) {
		Aluno aluno = alunoService.buscaAlunoPeloCodigo(codigoAluno);
		return registroAlimentarRepository.findAllByAluno(aluno, pageable);
	}

	public Optional<RegistroAlimentar> findById(Long codigo) {
		return registroAlimentarRepository.findById(codigo);
	}

	public void deleteById(Long codigo) {
		registroAlimentarRepository.deleteById(codigo);
	}

}
