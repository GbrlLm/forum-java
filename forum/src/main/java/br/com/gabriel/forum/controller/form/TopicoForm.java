package br.com.gabriel.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.gabriel.forum.model.Livro;
import br.com.gabriel.forum.model.Topico;
import br.com.gabriel.forum.repository.LivroRepository;

public class TopicoForm {
	
	@NotNull @NotEmpty
	private String titulo;
	
	@NotNull @NotEmpty
	private String mensagem;
	
	@NotNull @NotEmpty
	private String nomeLivro;
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public String getNomeLivro() {
		return nomeLivro;
	}
	
	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}

	public Topico converter(LivroRepository livroRepository) {
		
		Livro livro = livroRepository.findByNome(nomeLivro);
		return new Topico(titulo, mensagem, livro);
	
	}
}
