package br.com.geduca.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.geduca.api.model.RestricaoAlimentar;
import br.com.geduca.api.repository.RestricaoAlimentarRepository;

/**
 * @author gustavoclay
 *
 */
@Service
public class RestricaoAlimentarService {

	@Autowired
	private RestricaoAlimentarRepository restricaoAlimentarRepository;

	public RestricaoAlimentar salvar(RestricaoAlimentar restricaoAlimentar) {
		return restricaoAlimentarRepository.save(restricaoAlimentar);
	}

	public RestricaoAlimentar atualizar(Long codigo, RestricaoAlimentar restricaoAlimentar) {
		RestricaoAlimentar restricaoAlimentarSalva = buscarRestricaoAlimentarPeloCodigo(codigo);
		BeanUtils.copyProperties(restricaoAlimentar, restricaoAlimentarSalva, "codigo");
		return restricaoAlimentarRepository.save(restricaoAlimentarSalva);
	}

	public RestricaoAlimentar buscarRestricaoAlimentarPeloCodigo(Long codigo) {
		RestricaoAlimentar restricaoAlimentarSalva = restricaoAlimentarRepository.getOne(codigo);
		if (restricaoAlimentarSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return restricaoAlimentarSalva;
	}

	public Page<RestricaoAlimentar> findAll(Pageable pageable) {
		return restricaoAlimentarRepository.findAll(pageable);
	}
	
	public List<RestricaoAlimentar> findAllList() {
		return restricaoAlimentarRepository.findAll();
	}
	
	public Optional<RestricaoAlimentar> findById(long codigo) {
		return restricaoAlimentarRepository.findById(codigo);
	}

	public void deleteById(Long codigo) {
		restricaoAlimentarRepository.deleteById(codigo);
	}

}
