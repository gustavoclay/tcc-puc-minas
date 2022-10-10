package br.com.geduca.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.geduca.api.model.Despensa;
import br.com.geduca.api.model.Estoque;
import br.com.geduca.api.model.Produto;
import br.com.geduca.api.repository.custom.EstoqueRepositoryCustom;

/**
 * @author gustavoclay
 *
 */
@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long>, EstoqueRepositoryCustom {

	Page<Estoque> findAllByDespensa(Pageable pageable, Despensa despensa);

	List<Estoque> findAllByProduto(Produto produto);

}
