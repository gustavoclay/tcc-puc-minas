package br.com.geduca.api.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.geduca.api.model.Funcionario;
import br.com.geduca.api.model.Pessoa;
import br.com.geduca.api.repository.FuncionarioRepository;

/**
 * @author gustavoclay
 *
 */
@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private PessoaService pessoaService;

	public Funcionario salvar(Funcionario funcionario) {
		funcionario.setDataMatricula(LocalDate.now());
		pessoaService.salvar(funcionario.getPessoa());
		return funcionarioRepository.save(funcionario);
	}

	public Funcionario atualizar(Long codigo, Funcionario funcionario) {
		Funcionario funcionarioSalvo = buscaFuncionarioPeloCodigo(codigo);
		Pessoa pessoaSalvo = pessoaService.buscarPessoaPeloCodigo(funcionarioSalvo.getPessoa().getCodigo());
		BeanUtils.copyProperties(funcionario, funcionarioSalvo, "codigo");
		BeanUtils.copyProperties(funcionario.getPessoa(), pessoaSalvo, "codigo");
		pessoaService.salvar(pessoaSalvo);
		return funcionarioRepository.save(funcionarioSalvo);
	}

	public Funcionario buscaFuncionarioPeloCodigo(Long codigo) {
		Funcionario funcionarioSalvo = funcionarioRepository.getOne(codigo);
		if (funcionarioSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return funcionarioSalvo;
	}

	public Page<Funcionario> findAll(Pageable pageable) {
		return funcionarioRepository.findAll(pageable);
	}

	public List<Funcionario> findAllList() {
		return funcionarioRepository.findAll();
	}

	public Optional<Funcionario> findById(long codigo) {
		return funcionarioRepository.findById(codigo);
	}

	public void deleteById(Long codigo) {
		funcionarioRepository.deleteById(codigo);
	}

}
