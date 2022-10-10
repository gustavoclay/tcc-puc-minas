package br.com.geduca.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.geduca.api.model.Despensa;

/**
 * @author gustavoclay
 *
 */
@Repository
public interface DespensaRepository extends JpaRepository<Despensa, Long> {

	Page<Despensa> findByNomeContaining(String nome, Pageable paginacao);

}
