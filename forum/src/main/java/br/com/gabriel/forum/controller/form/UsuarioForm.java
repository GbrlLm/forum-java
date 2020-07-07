package br.com.gabriel.forum.controller.form;

import br.com.gabriel.forum.model.Livro;
import br.com.gabriel.forum.model.Usuario;
import br.com.gabriel.forum.repository.LivroRepository;
import br.com.gabriel.forum.repository.UsuarioRepository;

public class UsuarioForm {

	private String nome;
	private String email;
	private String senha;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Usuario atualizar(Long id, UsuarioRepository usuarioRepository) {
		
		Usuario usuario = usuarioRepository.getOne(id);
		
		if(this.nome != null || !this.nome.isEmpty()) {
			usuario.setNome(this.nome);
		} else 		
		if(this.email != null || !this.email.isEmpty()) {
			usuario.setEmail(this.email);
		} else		
		if(this.senha != null || !this.senha.isEmpty()) {
			usuario.setSenha(this.senha);
		}
		else {
			usuario.setNome(usuario.getNome());
			usuario.setEmail(usuario.getEmail());
			usuario.setSenha(usuario.getSenha());
		}
		
		return usuario;
	}
}
