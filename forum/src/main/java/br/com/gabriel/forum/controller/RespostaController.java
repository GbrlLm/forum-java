package br.com.gabriel.forum.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gabriel.forum.controller.dto.RespostaDto;
import br.com.gabriel.forum.controller.form.RespostaForm;
import br.com.gabriel.forum.model.Resposta;
import br.com.gabriel.forum.repository.RespostaRepository;
import br.com.gabriel.forum.repository.TopicoRepository;
import br.com.gabriel.forum.repository.UsuarioRepository;

@RestController
@RequestMapping("/respostas")
public class RespostaController {

	@Autowired
	RespostaRepository respostaRepository;
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@GetMapping
	public Page<RespostaDto> lista(@RequestParam(required = false) Long idTopico, 
			@PageableDefault(direction = Direction.ASC, page = 0, size = 5) Pageable paginacao){
		
		System.out.println("ID TOPICO" + idTopico);
		
		Page<Resposta> resposta;
		
		if(idTopico == null) {
			resposta = respostaRepository.findAll(paginacao);
		} 
		else {
			resposta = respostaRepository.findByTopico_Id(idTopico, paginacao);
		}
		
		
		return RespostaDto.converter(resposta);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<RespostaDto> cadastrar(@RequestBody @Valid RespostaForm form, UriComponentsBuilder uriBuilder) {
		
		Resposta resposta = form.converter(topicoRepository, usuarioRepository);
		respostaRepository.save(resposta);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(resposta.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new RespostaDto(resposta));
	}
}
