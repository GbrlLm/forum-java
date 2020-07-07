package br.com.gabriel.forum.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.gabriel.forum.model.Livro;
import br.com.gabriel.forum.model.Topico;
import br.com.gabriel.forum.model.Usuario;
import br.com.gabriel.forum.repository.LivroRepository;
import br.com.gabriel.forum.repository.TopicoRepository;
import br.com.gabriel.forum.repository.UsuarioRepository;

public class LivroForm {
	@NotNull @NotEmpty
	private String nome;
	
	@NotNull @NotEmpty
	private String categoria;
	
	private Long idAutor;
	
	public Long getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(Long idAutor) {
		this.idAutor = idAutor;
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
	
	public Livro atualizar(Long id, LivroRepository livroRepository) {
		
		Livro livro = livroRepository.getOne(id);
		
		livro.setNome(this.nome);
		livro.setCategoria(this.categoria);
		
		return livro;
	}
	
	public Livro converter(UsuarioRepository usuarioRepository) {
				
		Optional<Usuario> usuario = usuarioRepository.findById(idAutor);
		
		if(usuario.isPresent()) {
			return new Livro(nome, categoria, usuario.get());
		}
		
		return null;
	}
}
