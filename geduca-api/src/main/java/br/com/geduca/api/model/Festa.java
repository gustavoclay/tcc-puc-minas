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
 * Classe Festa
 * 
 * @ @author gustavoclay
 * 
 */
@Setter
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "tb_festa")
public class Festa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_festa")
	private Long codigo;

	private String nome;

	@ManyToOne
	private Aluno aluno;

	private LocalDate data;

	private Long duracao;

	private String observacoes;
}
