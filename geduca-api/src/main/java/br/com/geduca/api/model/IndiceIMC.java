package br.com.geduca.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.geduca.api.model.enums.SexoEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
* Classe IndiceIMC
* 
* @author gustavoclay
* 
*/
@Setter
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "tb_indice_imc")
public class IndiceIMC {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_indice_imc")
	private Long codigo;
	
	private int idade;
	
	private SexoEnum sexo;

	private Float minimo;

	private Float normal;
	
	private Float maximo;
}
