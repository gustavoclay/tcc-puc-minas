package br.com.geduca.api.model;

import java.time.LocalDate;

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
 * Classe RegistroAlimentar
 * 
 * @author gustavoclay
 * 
 */
@Setter
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "tb_registro_alimentar")
public class RegistroAlimentar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_registro_alimentar")
	private Long codigo;

	@ManyToOne
	private Aluno aluno;

	@ManyToOne
	private Receita receita;

	private String observacoes;

	@Column(name = "dt_registro")
	private LocalDate dataRegistro;

}
