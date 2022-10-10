package br.com.geduca.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.geduca.api.model.Aluno;
import br.com.geduca.api.model.AlunoRestricaoAlimentar;
import br.com.geduca.api.model.RestricaoAlimentar;
import br.com.geduca.api.repository.AlunoRestricaoAlimentarRepository;

/**
 * @author gustavoclay
 *
 */
@Service
public class AlunoRestricaoAlimentarService {

	@Autowired
	private AlunoService alunoService;

	@Autowired
	private AlunoRestricaoAlimentarRepository alunoRestricaoAlimentarRepository;

	public ResponseEntity<Object> save(Long codigoAluno, List<RestricaoAlimentar> restricoes) {
		Aluno aluno = alunoService.buscaAlunoPeloCodigo(codigoAluno);
		if (aluno != null) {
			if(!restricoes.isEmpty() ) {
				restricoes.forEach(restricao -> {
					AlunoRestricaoAlimentar arl = new AlunoRestricaoAlimentar();
					arl.setAluno(aluno);
					arl.setRestricaoAlimentar(restricao);					
					alunoRestricaoAlimentarRepository.save(arl);	
				});
				return ResponseEntity.ok().build();	
			}
		}
		return ResponseEntity.badRequest().build();
	}

	public void deleteById(Long codigo) {
		alunoRestricaoAlimentarRepository.deleteById(codigo);
	}
	
	public List<AlunoRestricaoAlimentar> listaTodos() {
		return alunoRestricaoAlimentarRepository.findAll();
	}

	public List<AlunoRestricaoAlimentar> getByAluno(Long codigoAluno) {
		Aluno aluno = alunoService.buscaAlunoPeloCodigo(codigoAluno);
		if (aluno != null) {
			return alunoRestricaoAlimentarRepository.findAllByAluno(aluno);
		}
		return null;
	}
}
