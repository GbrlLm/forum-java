package br.com.gabriel.forum.controller.dto;

import org.springframework.data.domain.Page;

import br.com.gabriel.forum.model.Livro;
import br.com.gabriel.forum.model.Topico;

public class LivroDto {
	
	private Long id;
	private String nome;
	private String categoria;
	
	public LivroDto(Livro livro) {
		this.id = livro.getId();
		this.nome = livro.getNome();
		this.categoria = livro.getCategoria();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long idLivro) {
		this.id = idLivro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public static Page<LivroDto> converter(Page<Livro> livros) {		
		return livros.map(LivroDto::new);
	}
	
}
