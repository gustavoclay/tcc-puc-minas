package br.com.geduca.api.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.geduca.api.model.dao.EstoqueDAO;

/**
 * 
 * @author gustavoclay
 * 
 */
@Repository
public interface EstoqueRepositoryCustom {

	Page<EstoqueDAO> listarEstoquePorDespensa(Long codigoDespensa, Pageable paginacao);

}
