package br.com.gabriel.forum.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gabriel.forum.model.Resposta;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, Long>{

	Page<Resposta> findByTopico_Id(Long idTopico, Pageable paginacao);
	
	Page<Resposta> findAll(Pageable paginacao);

	
}
