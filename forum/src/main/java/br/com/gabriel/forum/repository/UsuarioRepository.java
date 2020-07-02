package br.com.gabriel.forum.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.gabriel.forum.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	Optional<Usuario> findByEmail(String email);
	
	Optional<Usuario> findById(Long id);
	
	@Query("SELECT u FROM  Usuario u where u.id = : id")
	Usuario acharPorId(@Param("id") Long id);
}
