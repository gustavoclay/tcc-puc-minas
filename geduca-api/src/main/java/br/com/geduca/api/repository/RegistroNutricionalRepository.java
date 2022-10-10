package br.com.geduca.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.geduca.api.model.Aluno;
import br.com.geduca.api.model.RegistroNutricional;

/**
 * 
 * @author gustavoclay
 * 
 */
@Repository
public interface RegistroNutricionalRepository extends JpaRepository<RegistroNutricional, Long> {

	Page<RegistroNutricional> findAllByAluno(Aluno aluno, Pageable pageable);

}
