package br.com.geduca.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.geduca.api.model.Turma;

/**
 * @author gustavoclay
 *
 */
@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {

	public Page<Turma> findByNomeContaining(String nome, Pageable pageable);

}
