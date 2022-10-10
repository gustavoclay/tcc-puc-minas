package br.com.geduca.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe AlunoRestricaoAlimentar
 * 
 * @author gustavoclay
 * 
 */
@Setter
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "rl_aluno_restricao_alimentar")
public class AlunoRestricaoAlimentar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_aluno_restricao_alimentar")
	private Long codigo;

	@ManyToOne
	private Aluno aluno;

	@ManyToOne
	private RestricaoAlimentar restricaoAlimentar;

}
