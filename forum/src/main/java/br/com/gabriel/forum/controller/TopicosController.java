package br.com.gabriel.forum.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gabriel.forum.controller.dto.DetalhesTopicoDto;
import br.com.gabriel.forum.controller.dto.RespostaDto;
import br.com.gabriel.forum.controller.dto.TopicoDto;
import br.com.gabriel.forum.controller.form.AtualizacaoTopicoForm;
import br.com.gabriel.forum.controller.form.TopicoForm;
import br.com.gabriel.forum.model.Resposta;
import br.com.gabriel.forum.model.Topico;
import br.com.gabriel.forum.repository.LivroRepository;
import br.com.gabriel.forum.repository.TopicoRepository;
import br.com.gabriel.forum.repository.UsuarioRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	

	@GetMapping
	public Page<TopicoDto> lista(@RequestParam(required = true) Long idLivro,
			@PageableDefault(sort = "dataCriacao", direction = Direction.ASC, page = 0, size = 5) Pageable paginacao){
		
		Page<Topico> topicos;
		
		if(idLivro == null) {
			topicos = topicoRepository.findAll(paginacao);
		} 
		else {
			topicos = topicoRepository.findByLivroId(idLivro, paginacao);
		}
		
		return TopicoDto.converter(topicos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Page<TopicoDto>> meuLivros(@PathVariable("id") Long id,
			@PageableDefault(sort = "mensagem", direction = Direction.ASC, page = 0, size = 5) Pageable paginacao) {
		
		Page<Topico> topicos = topicoRepository.findByAutor_id(id, paginacao);
		
		if(topicos.isEmpty() || topicos == null) {
			return ResponseEntity.notFound().build(); 
		}
		else {
			return ResponseEntity.ok(TopicoDto.converter(topicos));
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
		Topico topico = form.converter(livroRepository, usuarioRepository);
		topicoRepository.save(topico);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDto(topico));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form){
		Optional<Topico> opt = topicoRepository.findById(id);
		
		
		if(opt.isPresent()) {
			Topico topico = form.atualizar(id, topicoRepository);
			return ResponseEntity.ok(new TopicoDto(topico));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Topico> opt = topicoRepository.findById(id);
		
		
		if(opt.isPresent()) {
			topicoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build(); 
	}
}
