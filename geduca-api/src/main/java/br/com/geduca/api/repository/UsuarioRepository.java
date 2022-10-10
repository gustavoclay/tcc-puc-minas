package br.com.geduca.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.geduca.api.model.Usuario;

/**
 * @author gustavoclay
 *
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Optional<Usuario> findByUsername(String username);

	public Optional<Usuario> findByEmail(String email);

}
