package br.com.geduca.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.geduca.api.model.enums.TipoSanguineoEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe FichaSaude
 * 
 * @author gustavoclay
 * 
 */
@Setter
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "tb_ficha_saude")
public class FichaSaude {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ficha_saude")
	private Long codigo;

	@OneToOne
	private Aluno aluno;

	@Column(name = "cartao_sus")
	private String cartaoSus;

	@Enumerated
	private TipoSanguineoEnum tipoSanguineo;

	private Boolean doenca;

	@Column(name = "doenca_descricao")
	private String doencaDescricao;

	private Boolean medicamento;

	@Column(name = "medicamento_descricao")
	private String medicamentoDescricao;

	private Boolean suplemento;

	@Column(name = "suplemento_descricao")
	private String suplementoDescricao;

	private Boolean deficiencia;

	@Column(name = "deficiencia_descricao")
	private String deficienciaDescricao;

	private Boolean alergia;

	@Column(name = "alergia_descricao")
	private String alergiaDescricao;

	private Boolean intolerancia;

	@Column(name = "intolerancia_descricao")
	private String intoleranciaDescricao;

}
