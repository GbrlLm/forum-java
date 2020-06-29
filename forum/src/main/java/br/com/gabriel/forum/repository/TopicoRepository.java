package br.com.gabriel.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gabriel.forum.model.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

}
