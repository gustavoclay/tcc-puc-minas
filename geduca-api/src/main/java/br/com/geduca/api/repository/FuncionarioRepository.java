package br.com.geduca.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.geduca.api.model.Funcionario;

/**
 * @author gustavoclay
 *
 */
@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}
