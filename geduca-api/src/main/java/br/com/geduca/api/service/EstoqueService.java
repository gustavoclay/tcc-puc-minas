package br.com.geduca.api.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.geduca.api.model.Despensa;
import br.com.geduca.api.model.Estoque;
import br.com.geduca.api.model.Produto;
import br.com.geduca.api.model.dao.EstoqueDAO;
import br.com.geduca.api.repository.EstoqueRepository;

/**
 * @author gustavoclay
 *
 */
@Service
public class EstoqueService {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private DespensaService despensaService;

	@Autowired
	private EstoqueRepository estoqueRepository;

	public Estoque save(Estoque estoque) {
		estoque.setDataRegistro(LocalDate.now());
		return estoqueRepository.save(estoque);
	}

	public Estoque atualizar(Long codigo, Estoque estoque) {
		estoque.setDataRegistro(LocalDate.now());
		Estoque estoqueSalvo = buscarEstoquePeloCodigo(codigo);
		BeanUtils.copyProperties(estoque, estoqueSalvo, "codigo");
		return estoqueRepository.save(estoqueSalvo);
	}

	public Estoque buscarEstoquePeloCodigo(Long codigo) {
		Estoque estoque = estoqueRepository.getOne(codigo);
		if (estoque == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return estoque;
	}

	public void deleteById(Long codigo) {
		estoqueRepository.deleteById(codigo);
	}
	
	public Page<Estoque> lista(Pageable pageable) {
		return estoqueRepository.findAll(pageable);
	}

	public List<Estoque> listaTodos() {
		return estoqueRepository.findAll();
	}

	public Page<Estoque> getByDispensa(Pageable pageable, Long codigoDespensa) {
		Despensa despensa = despensaService.buscarDispensaPeloCodigo(codigoDespensa);
		if (despensa != null) {
			return estoqueRepository.findAllByDespensa(pageable, despensa);
		}
		return null;
	}

	public List<Estoque> getByProduto(Long codigoProduto) {
		Produto produto = produtoService.buscaProdutoPeloCodigo(codigoProduto);
		if (produto != null) {
			return estoqueRepository.findAllByProduto(produto);
		}
		return null;
	}
	
	public Page<EstoqueDAO> listaPorDespensa(Long codigoDespensa, Pageable paginacao) {
		return estoqueRepository.listarEstoquePorDespensa(codigoDespensa, paginacao);
	}

}
