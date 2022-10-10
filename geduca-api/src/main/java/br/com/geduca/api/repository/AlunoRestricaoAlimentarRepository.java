package br.com.geduca.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.geduca.api.model.Aluno;
import br.com.geduca.api.model.AlunoRestricaoAlimentar;

/**
 * @author gustavoclay
 *
 */
@Repository
public interface AlunoRestricaoAlimentarRepository extends JpaRepository<AlunoRestricaoAlimentar, Long> {

	public List<AlunoRestricaoAlimentar> findAllByAluno(Aluno aluno);

}
