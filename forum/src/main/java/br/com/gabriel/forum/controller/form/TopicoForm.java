package br.com.gabriel.forum.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.gabriel.forum.model.Livro;
import br.com.gabriel.forum.model.Topico;
import br.com.gabriel.forum.model.Usuario;
import br.com.gabriel.forum.repository.LivroRepository;
import br.com.gabriel.forum.repository.UsuarioRepository;

public class TopicoForm {
	
	@NotNull @NotEmpty
	private String titulo;
	
	@NotNull @NotEmpty
	private String mensagem;
	
	private Long idLivro;
	
	private Long idAutor;
	
	public Long getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(Long idAutor) {
		this.idAutor = idAutor;
	}

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
	
	public Long getIdLivro() {
		return idLivro;
	}
	
	public void setIdLivro(Long idLivro) {
		this.idLivro = idLivro;
	}

	public Topico converter(LivroRepository livroRepository, UsuarioRepository usuarioRepository) {
		
		Optional<Livro> livro = livroRepository.findById(idLivro);
		
		Optional<Usuario> usuario = usuarioRepository.findById(idAutor);
		
		if(usuario.isPresent()) {			
			if(livro.isPresent()) {
				return new Topico(titulo, mensagem, livro.get(), usuario.get());
			}
		}
		
		return null;
	}
}
