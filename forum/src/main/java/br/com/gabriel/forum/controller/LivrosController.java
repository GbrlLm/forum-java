package br.com.gabriel.forum.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
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
import br.com.gabriel.forum.controller.dto.LivroDto;
import br.com.gabriel.forum.controller.dto.TopicoDto;
import br.com.gabriel.forum.controller.form.AtualizacaoTopicoForm;
import br.com.gabriel.forum.controller.form.LivroForm;
import br.com.gabriel.forum.controller.form.TopicoForm;
import br.com.gabriel.forum.model.Livro;
import br.com.gabriel.forum.model.Topico;
import br.com.gabriel.forum.repository.LivroRepository;

@RestController
@RequestMapping("/livros")
public class LivrosController {
	
	@Autowired
	private LivroRepository livroRepository;
	
	@GetMapping
	public Page<LivroDto> lista(@RequestParam(required = false) String nomeLivro,
			@PageableDefault(sort = "nome", direction = Direction.ASC, page = 0, size = 5) Pageable paginacao){
		
		Page<Livro> livros;
		
		if(nomeLivro == null) {
			livros = livroRepository.findAll(paginacao);
		} 
		else {
			livros = livroRepository.findByNome(nomeLivro, paginacao);
		}
		
		return LivroDto.converter(livros);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LivroDto> detalhar(@PathVariable("id") Long id) {
		
		Optional<Livro> livro = livroRepository.findById(id);
		
		if(livro.isPresent()) {
			return ResponseEntity.ok(new LivroDto(livro.get()));
		}
		
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<LivroDto> cadastrar(@RequestBody @Valid LivroForm form, UriComponentsBuilder uriBuilder) {
		
		Livro livro = form.converter(livroRepository);
		livroRepository.save(livro);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(livro.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new LivroDto(livro));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<LivroDto> atualizar(@PathVariable Long id, @RequestBody @Valid LivroForm form){
		Optional<Livro> opt = livroRepository.findById(id);
		
		
		if(opt.isPresent()) {
			Livro livro = form.atualizar(id, livroRepository);
			return ResponseEntity.ok(new LivroDto(livro));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Livro> opt = livroRepository.findById(id);
		
		
		if(opt.isPresent()) {
			livroRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build(); 
	}

}
