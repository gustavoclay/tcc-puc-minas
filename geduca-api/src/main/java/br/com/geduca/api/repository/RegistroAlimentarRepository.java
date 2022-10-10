package br.com.geduca.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.geduca.api.model.Aluno;
import br.com.geduca.api.model.RegistroAlimentar;

/**
 * 
 * @author gustavoclay
 * 
 */
@Repository
public interface RegistroAlimentarRepository extends JpaRepository<RegistroAlimentar, Long> {

	Page<RegistroAlimentar> findAllByAluno(Aluno aluno, Pageable pageable);
}
