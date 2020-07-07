package br.com.gabriel.forum.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gabriel.forum.controller.dto.LivroDto;
import br.com.gabriel.forum.controller.dto.RespostaDto;
import br.com.gabriel.forum.controller.dto.UsuarioDto;
import br.com.gabriel.forum.controller.form.LivroForm;
import br.com.gabriel.forum.controller.form.RespostaForm;
import br.com.gabriel.forum.controller.form.UsuarioForm;
import br.com.gabriel.forum.model.Livro;
import br.com.gabriel.forum.model.Resposta;
import br.com.gabriel.forum.model.Usuario;
import br.com.gabriel.forum.repository.UsuarioRepository;



@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDto> detalhar(@PathVariable("id") Long id) {
		
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		if(usuario.isPresent()) {
			return ResponseEntity.ok(new UsuarioDto(usuario.get()));
		}
		
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder) {
		
		Usuario usuario = new Usuario(
				form.getNome(), 
				form.getEmail(), 
				new BCryptPasswordEncoder().encode(form.getSenha())
				);
		
		usuarioRepository.save(usuario);
		
		URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UsuarioDto> atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioForm form){
		Optional<Usuario> opt = usuarioRepository.findById(id);
		
		System.out.println("AAAAA");
		System.out.println(form.getEmail().toString() + form.getNome().toString() + form.getSenha().toString());
		
		if(opt.isPresent()) {
			Usuario usuario = form.atualizar(id, usuarioRepository);
			return ResponseEntity.ok(new UsuarioDto(usuario));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Usuario> opt = usuarioRepository.findById(id);
		
		
		if(opt.isPresent()) {
			usuarioRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build(); 
	}
}
