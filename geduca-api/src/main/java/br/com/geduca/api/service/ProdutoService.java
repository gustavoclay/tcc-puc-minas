package br.com.geduca.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.geduca.api.model.Produto;
import br.com.geduca.api.repository.ProdutoRepository;

/**
 * @author gustavoclay
 *
 */
@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}

	public Produto atualizar(Long codigo, Produto produto) {
		Produto produtoSalvo = produtoRepository.findById(codigo).get();
		produtoSalvo = produto;
		return produtoRepository.save(produtoSalvo);
	}

	public Produto buscaProdutoPeloCodigo(Long codigo) {
		Produto produtoSalvo = produtoRepository.getOne(codigo);
		if (produtoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return produtoSalvo;
	}

	public Page<Produto> findAll(Pageable pageable) {
		return produtoRepository.findAll(pageable);
	}

	public Optional<Produto> findById(long codigo) {
		return produtoRepository.findById(codigo);
	}

	public void deleteById(Long codigo) {
		produtoRepository.deleteById(codigo);
	}

	public List<Produto> findAllList() {
		return produtoRepository.findAll();
	}

}
