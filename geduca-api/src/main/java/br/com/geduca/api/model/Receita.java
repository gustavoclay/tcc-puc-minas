package br.com.geduca.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.geduca.api.model.enums.TipoReceitaEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe Receita
 * 
 * @author gustavoclay
 * 
 */

@Setter
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "tb_receita")
public class Receita {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_receita")
	private Long codigo;

	private String nome;

	private String descricao;

	@Column(length = 1000)
	private String ingredientes;

	@Column(name = "modo_preparo", length = 1000)
	private String modoPreparo;

	@Enumerated
	private TipoReceitaEnum tipoReceita;

}
