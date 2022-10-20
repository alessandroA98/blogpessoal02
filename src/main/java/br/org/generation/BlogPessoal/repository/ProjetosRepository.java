package br.org.generation.BlogPessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.org.generation.BlogPessoal.model.Projetos;

@Repository
public interface ProjetosRepository extends JpaRepository <Projetos, Long> {

	public List<Projetos> findAllByDescricaoContainingIgnoreCase(@Param ("descricao") String descricao);
}
