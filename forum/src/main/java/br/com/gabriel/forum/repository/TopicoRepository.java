package br.com.gabriel.forum.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.gabriel.forum.model.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

	Page<Topico> findByLivroNome(String nomeLivro, Pageable paginacao);

	@Query("SELECT  t FROM  Topico t where t.livro.nome = : nomeLivro")
	List<Topico> carregarPorNomeLivro(@Param("nomeLivro") String nomeLivro);
}
