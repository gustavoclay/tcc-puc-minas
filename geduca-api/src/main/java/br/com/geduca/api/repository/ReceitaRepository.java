package br.com.geduca.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.geduca.api.model.Receita;

/**
 * @author gustavoclay
 *
 */
@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

}
