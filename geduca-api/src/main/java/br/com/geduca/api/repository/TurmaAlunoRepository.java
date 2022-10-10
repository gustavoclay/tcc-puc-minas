package br.com.geduca.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.geduca.api.model.Aluno;
import br.com.geduca.api.model.Turma;
import br.com.geduca.api.model.TurmaAluno;

/**
 * @author gustavoclay
 *
 */
@Repository
public interface TurmaAlunoRepository extends JpaRepository<TurmaAluno, Long> {

	List<TurmaAluno> findAllByTurma(Turma turma);

	List<TurmaAluno> findAllByAluno(Aluno aluno);

}
