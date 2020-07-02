package br.com.gabriel.forum.controller.form;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.gabriel.forum.model.Resposta;
import br.com.gabriel.forum.model.Topico;
import br.com.gabriel.forum.model.Usuario;
import br.com.gabriel.forum.repository.TopicoRepository;
import br.com.gabriel.forum.repository.UsuarioRepository;

public class RespostaForm {
	
	
	

	@NotNull @NotEmpty
	private String mensagem;
	
	private Long idAutor;
	
	private Long idTopico;

	public String getMensagem() {
		return mensagem;
	}
	
	public Long getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(Long idAutor) {
		this.idAutor = idAutor;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public Long getIdTopico() {
		return idTopico;
	}

	public void setIdTopico(Long idTopico) {
		this.idTopico = idTopico;
	}

	
	public Resposta converter(TopicoRepository topicoRepository, UsuarioRepository usuarioRepository) {
		System.out.println("OIOIOI " + getIdTopico() + getIdAutor());
		System.out.println(getMensagem());
		
		
		Topico topico = topicoRepository.findById(getIdTopico()).get();
		System.out.println(topico);
		
		Usuario autor = usuarioRepository.findById(getIdAutor()).get();
		System.out.println(autor);
		
		return new Resposta(this.mensagem, autor, topico);
	}	
}
