package br.com.gabriel.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gabriel.forum.controller.dto.TopicoDto;
import br.com.gabriel.forum.model.Topico;
import br.com.gabriel.forum.repository.TopicoRepository;

@RestController
public class TopicosController {
	
	@Autowired
	private TopicoRepository topicoRepository;

	@RequestMapping("/topicos")
	public List<TopicoDto> lista(){
		
		List<Topico> topicos = topicoRepository.findAll();
		
		return TopicoDto.converter(topicos);
	}
}
