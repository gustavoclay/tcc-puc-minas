package br.com.geduca.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.geduca.api.model.Curso;

/**
 * 
 * @author gustavoclay
 * 
 */
@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

	public Page<Curso> findByNomeContaining(String nome, Pageable pageable);

}
