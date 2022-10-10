package br.com.geduca.api.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.geduca.api.model.enums.TipoMovimentacaoEstoqueEnum;
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
@Entity
@Table(name = "tb_estoque")
public class Estoque {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_estoque")
	private Long codigo;

	@ManyToOne
	private Produto produto;

	@ManyToOne
	private Despensa despensa;

	private Long quantidade;

	private String lote;

	private TipoMovimentacaoEstoqueEnum tipo;

	private LocalDate dataValidade;

	private LocalDate dataRegistro;

}
