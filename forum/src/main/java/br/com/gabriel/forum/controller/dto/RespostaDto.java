package br.com.gabriel.forum.controller.dto;

import java.time.LocalDateTime;

import br.com.gabriel.forum.model.Resposta;

public class RespostaDto {
	
	private Long id;
	private String mensagem;
	private LocalDateTime dataCriacao;
	private String nomeAutor;
	
	
	public RespostaDto(Resposta resp) {
		this.id = resp.getId();
		this.mensagem = resp.getMensagem();
		this.dataCriacao = resp.getDataCriacao();
		this.nomeAutor = resp.getAutor().getNome();
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}


	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}


	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}


	public Long getId() {
		return id;
	}


	public String getMensagem() {
		return mensagem;
	}


	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}


	public String getNomeAutor() {
		return nomeAutor;
	}
	
	
}
