package br.com.geduca.api.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.geduca.api.model.enums.ResultadoIMCEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe RegistroNutricional
 * 
 * @author gustavoclay
 * 
 */
@Setter
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "tb_registro_nutricional")
public class RegistroNutricional {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_registro_nutricional")
	private Long codigo;

	@ManyToOne
	private Aluno aluno;

	private float peso;

	private float altura;

	private float imc;

	private ResultadoIMCEnum resultado;

	@Column(name = "dt_registro")
	private LocalDate dataRegistro;

}
