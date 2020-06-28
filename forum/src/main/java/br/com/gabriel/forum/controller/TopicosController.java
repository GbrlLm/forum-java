package br.com.gabriel.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gabriel.forum.controller.dto.TopicoDto;
import br.com.gabriel.forum.model.Livro;
import br.com.gabriel.forum.model.Topico;

@RestController
public class TopicosController {

	@RequestMapping("/topicos")
	public List<TopicoDto> lista(){
		
		Topico top = new Topico("Duvida", "quem mata voldermort", new Livro("HP", "aventura"));
		
		return TopicoDto.converter(Arrays.asList(top, top, top));
	}
}
