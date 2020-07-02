package br.com.gabriel.forum.controller.dto;

import org.springframework.data.domain.Page;

import br.com.gabriel.forum.model.Livro;
import br.com.gabriel.forum.model.Topico;

public class LivroDto {
	
	private String nome;
	private String categoria;
	
	public LivroDto(Livro livro) {
		this.nome = livro.getNome();
		this.categoria = livro.getCategoria();
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
