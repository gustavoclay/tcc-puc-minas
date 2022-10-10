package br.com.geduca.api.model.dao;

import java.time.LocalDate;

import br.com.geduca.api.model.Produto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe Estoque
 * 
 * @author gustavoclay
 * 
 */

@Setter
@Getter
@EqualsAndHashCode
public class EstoqueDAO {

	private Produto produto;

	private String lote;

	private LocalDate dataValidade;

	private Long total;

}
