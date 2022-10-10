package br.com.geduca.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.geduca.api.model.IndiceIMC;
import br.com.geduca.api.model.enums.SexoEnum;

/**
 * @author gustavoclay
 *
 */
@Repository
public interface IndiceIMCRepository extends JpaRepository<IndiceIMC, Long> {

	@Query(value = "SELECT i FROM IndiceIMC i WHERE i.idade = :idade AND i.sexo = :sexo")
	IndiceIMC findByIdadeSexo(@Param("idade") int idade, @Param("sexo") SexoEnum sexo);

}
