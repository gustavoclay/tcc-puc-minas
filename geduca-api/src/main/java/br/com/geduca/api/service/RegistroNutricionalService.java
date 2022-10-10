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

import br.com.geduca.api.model.Aluno;
import br.com.geduca.api.model.IndiceIMC;
import br.com.geduca.api.model.RegistroNutricional;
import br.com.geduca.api.model.enums.ResultadoIMCEnum;
import br.com.geduca.api.model.enums.SexoEnum;
import br.com.geduca.api.repository.IndiceIMCRepository;
import br.com.geduca.api.repository.RegistroNutricionalRepository;

/**
 * 
 * @author gustavoclay
 * 
 */
@Service
public class RegistroNutricionalService {

	@Autowired
	private RegistroNutricionalRepository registroNutricionalRepository;

	@Autowired
	private AlunoService alunoService;

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private IndiceIMCRepository imcRepository;

	public RegistroNutricional salvar(RegistroNutricional registroNutricional) {
		Aluno aluno = alunoService.buscaAlunoPeloCodigo(registroNutricional.getAluno().getCodigo());
		int idade = pessoaService.calculaIdade(aluno.getPessoa());

		registroNutricional.setImc(calculaImc(registroNutricional.getPeso(), registroNutricional.getAltura()));
		registroNutricional
				.setResultado(resultadoImc(registroNutricional.getImc(), idade, aluno.getPessoa().getSexo()));
		registroNutricional.setDataRegistro(LocalDate.now());

		return registroNutricionalRepository.save(registroNutricional);
	}

	public RegistroNutricional atualizar(Long codigo, RegistroNutricional registroNutricional) {
		RegistroNutricional registroNutricionalSalvo = buscarRegistroNutricionalPeloCodigo(codigo);
		BeanUtils.copyProperties(registroNutricional, registroNutricionalSalvo, "codigo");
		Aluno aluno = alunoService.buscaAlunoPeloCodigo(registroNutricional.getAluno().getCodigo());

		int idade = pessoaService.calculaIdade(aluno.getPessoa());

		registroNutricional.setImc(calculaImc(registroNutricional.getPeso(), registroNutricional.getAltura()));
		registroNutricional
				.setResultado(resultadoImc(registroNutricional.getImc(), idade, aluno.getPessoa().getSexo()));
		registroNutricional.setDataRegistro(LocalDate.now());

		return registroNutricionalRepository.save(registroNutricionalSalvo);
	}

	public RegistroNutricional buscarRegistroNutricionalPeloCodigo(Long codigo) {
		RegistroNutricional registroNutricionalSalvo = registroNutricionalRepository.getOne(codigo);
		if (registroNutricionalSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return registroNutricionalSalvo;
	}

	public List<RegistroNutricional> findAllList() {
		return registroNutricionalRepository.findAll();
	}

	public Page<RegistroNutricional> findAllByAluno(Long codigoAluno, Pageable pageable) {
		Aluno aluno = alunoService.buscaAlunoPeloCodigo(codigoAluno);
		return registroNutricionalRepository.findAllByAluno(aluno, pageable);
	}

	public Optional<RegistroNutricional> findById(Long codigo) {
		return registroNutricionalRepository.findById(codigo);
	}

	public void deleteById(Long codigo) {
		registroNutricionalRepository.deleteById(codigo);
	}

	public float calculaImc(float peso, float altura) {
		return peso / (altura * altura);
	}

	public ResultadoIMCEnum resultadoImc(double imc, int idade, SexoEnum sexo) {
		if (idade < 2) {
			return ResultadoIMCEnum.INVALIDO;
		}

		if (idade >= 18) {
			if (imc <= 18.5) {
				return ResultadoIMCEnum.ABAIXO_DO_PESO;
			} else if (imc <= 24.9) {
				return ResultadoIMCEnum.PESO_NORMAL;
			} else if (imc <= 29.9) {
				return ResultadoIMCEnum.SOBRE_PESO;
			} else if (imc > 30) {
				return ResultadoIMCEnum.OBESIDADE;
			}
		}

		IndiceIMC indice = imcRepository.findByIdadeSexo(idade, sexo);

		if (imc <= indice.getMinimo()) {
			return ResultadoIMCEnum.ABAIXO_DO_PESO;
		} else if (imc <= indice.getNormal()) {
			return ResultadoIMCEnum.PESO_NORMAL;
		} else if (imc <= indice.getMaximo()) {
			return ResultadoIMCEnum.SOBRE_PESO;
		} else if (imc > indice.getMaximo()) {
			return ResultadoIMCEnum.OBESIDADE;
		}

		return ResultadoIMCEnum.INVALIDO;
	}

}
