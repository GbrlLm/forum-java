package br.com.gabriel.forum.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gabriel.forum.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

	Page<Livro> findByNome(String nomeLivro, Pageable paginacao);
	
	Page<Livro> findByAutor_id(Long id, Pageable paginacao);

	
	Livro findByNome(String nomeLivro);
}
