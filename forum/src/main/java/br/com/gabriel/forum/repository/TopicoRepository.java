package br.com.gabriel.forum.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.gabriel.forum.model.Livro;
import br.com.gabriel.forum.model.Topico;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {

	Page<Topico> findByLivroNome(String nomeLivro, Pageable paginacao);
	
	Page<Topico> findByLivroId(Long idLivro, Pageable paginacao);
	
	Page<Topico> findByAutor_id(Long id, Pageable paginacao);
	
	Optional<Topico> findById(Long id);
	
	@Query("SELECT  t FROM  Topico t where t.id = : id")
	Topico acharPorId(@Param("id") Long id);

	@Query("SELECT  t FROM  Topico t where t.livro.nome = : nomeLivro")
	List<Topico> carregarPorNomeLivro(@Param("nomeLivro") String nomeLivro);
}
