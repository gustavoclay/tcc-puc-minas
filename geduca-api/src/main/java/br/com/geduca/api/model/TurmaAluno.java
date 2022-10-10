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
 * Classe TurmaAluno
 * 
 * @author gustavoclay
 * 
 */
@Setter
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "rl_turma_aluno")
public class TurmaAluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_turma_aluno")
	private Long codigo;

	@ManyToOne
	private Aluno aluno;

	@ManyToOne
	private Turma turma;

}
