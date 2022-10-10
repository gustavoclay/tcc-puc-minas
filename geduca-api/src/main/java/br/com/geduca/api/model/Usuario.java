package br.com.geduca.api.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe Usuario
 * 
 * @author gustavoclay
 *
 */

@Setter
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "tb_usuario")
public class Usuario {

	@Id
	@Column(name = "id_usuario")
	private Long codigo;

	private String username;

	private String email;

	private String password;

	@ManyToOne
	private Pessoa pessoa;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "rl_usuario_permissao", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_permissao"))
	private List<Permissao> permissoes;
}
