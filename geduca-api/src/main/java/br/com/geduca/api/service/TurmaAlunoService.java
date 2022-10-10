package br.com.geduca.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.geduca.api.model.Aluno;
import br.com.geduca.api.model.Turma;
import br.com.geduca.api.model.TurmaAluno;
import br.com.geduca.api.repository.TurmaAlunoRepository;

/**
 * @author gustavoclay
 *
 */
@Service
public class TurmaAlunoService {

	@Autowired
	private AlunoService alunoService;

	@Autowired
	private TurmaService turmaService;

	@Autowired
	private TurmaAlunoRepository turmaAlunoRepository;

	public ResponseEntity<Object> save(Long codigoTurma, List<Aluno> alunos) {
		Turma turma = turmaService.buscarTurmaPeloCodigo(codigoTurma);
		if (turma != null) {
			if (!alunos.isEmpty()) {
				alunos.forEach(aluno -> {
					TurmaAluno turmaAluno = new TurmaAluno();
					turmaAluno.setAluno(aluno);
					turmaAluno.setTurma(turma);
					turmaAlunoRepository.save(turmaAluno);
				});
				return ResponseEntity.ok().build();
			}
		}
		return ResponseEntity.badRequest().build();
	}

	public void deleteById(Long codigo) {
		turmaAlunoRepository.deleteById(codigo);
	}

	public List<TurmaAluno> listaTodos() {
		return turmaAlunoRepository.findAll();
	}

	public List<TurmaAluno> getByTurma(Long codigoTurma) {
		Turma turma = turmaService.buscarTurmaPeloCodigo(codigoTurma);
		if (turma != null) {
			return turmaAlunoRepository.findAllByTurma(turma);
		}
		return null;
	}
	
	public List<TurmaAluno> getByAluno(Long codigoAluno) {
		Aluno aluno = alunoService.buscaAlunoPeloCodigo(codigoAluno);
		if (aluno != null) {
			return turmaAlunoRepository.findAllByAluno(aluno);
		}
		return null;
	}

}
