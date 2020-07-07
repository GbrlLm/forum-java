package br.com.gabriel.forum.controller.dto;

import org.springframework.data.domain.Page;

import br.com.gabriel.forum.model.Livro;
import br.com.gabriel.forum.model.Usuario;

public class UsuarioDto {
	
	private Long id;
	private String nome;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
	}
}
