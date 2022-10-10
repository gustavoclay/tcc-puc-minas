package br.com.geduca.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.geduca.api.model.Aluno;

/**
 * @author gustavoclay
 *
 */
@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}
