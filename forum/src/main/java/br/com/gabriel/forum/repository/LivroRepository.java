package br.com.gabriel.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gabriel.forum.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

	Livro findByNome(String nomeLivro);

}
