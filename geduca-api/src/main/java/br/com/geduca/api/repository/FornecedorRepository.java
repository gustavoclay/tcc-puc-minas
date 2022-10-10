package br.com.geduca.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.geduca.api.model.Fornecedor;

/**
 * @author gustavoclay
 *
 */
@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

}
