package br.com.geduca.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe Permissao
 * 
 * @author gustavoclay
 *
 */
@Setter
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "tb_permissao")
public class Permissao {

	@Id
	@Column(name = "id_permissao")
	private Long codigo;

	private String descricao;
}
