package br.com.geduca.api.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.geduca.api.model.dao.EstoqueDAO;
import br.com.geduca.api.repository.custom.EstoqueRepositoryCustom;

/**
 * 
 * @author gustavoclay
 * 
 */
public class EstoqueRepositoryImpl implements EstoqueRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Page<EstoqueDAO> listarEstoquePorDespensa(Long codigoDespensa, Pageable paginacao) {
		String hql = " SELECT e.produto, e.lote, e.dataValidade, SUM(e.quantidade) AS total FROM Estoque e "
				+ " %s WHERE e.despensa.codigo = :codigoDespensa %s ";

		String joins = " JOIN e.produto po JOIN e.despensa de ";

		String groupBy = " GROUP BY e.produto, po, e.lote, e.dataValidade ";

		Query query = entityManager.createQuery(String.format(hql, joins, groupBy));
		query.setParameter("codigoDespensa", codigoDespensa);

		int total = query.getResultList().size();

		if (total > 0) {
			query.setMaxResults(paginacao.getPageSize() > 0 ? paginacao.getPageSize() : 10);
			query.setFirstResult(paginacao.getPageNumber() * paginacao.getPageSize());

			Page<EstoqueDAO> resultado = new PageImpl<EstoqueDAO>(query.getResultList(), paginacao, total);

			return resultado;
		}
		return Page.empty(paginacao);
	}

}
