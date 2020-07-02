package br.com.gabriel.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.gabriel.forum.model.Livro;
import br.com.gabriel.forum.model.Topico;
import br.com.gabriel.forum.repository.LivroRepository;
import br.com.gabriel.forum.repository.TopicoRepository;

public class LivroForm {
	@NotNull @NotEmpty
	private String nome;
	
	@NotNull @NotEmpty
	private String categoria;
	
	
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


	public Livro converter(LivroRepository livroRepository) {
		return new Livro(nome, categoria);
	}
	
	public Livro atualizar(Long id, LivroRepository livroRepository) {
		
		Livro livro = livroRepository.getOne(id);
		
		livro.setNome(this.nome);
		livro.setCategoria(this.categoria);
		
		return livro;
	}
}
