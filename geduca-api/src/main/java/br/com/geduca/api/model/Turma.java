package br.com.geduca.api.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.geduca.api.model.enums.PeriodoEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe Turma
 * 
 * @author gustavoclay
 * 
 */

@Setter
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "tb_turma")
public class Turma {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_turma")
	private Long codigo;

	private String nome;

	private String sala;

	private Boolean ativo;

	@ManyToOne
	@JoinColumn
	private Curso curso;

	@Enumerated
	private PeriodoEnum periodo;

	@Column(name = "dt_inicio")
	private LocalDate dataInicio;

	@Column(name = "dt_fim")
	private LocalDate dataFim;

}
