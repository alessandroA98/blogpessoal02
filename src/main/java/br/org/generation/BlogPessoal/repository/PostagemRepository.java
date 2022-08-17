package br.org.generation.BlogPessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.org.generation.BlogPessoal.model.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {
	
	
	public List<Postagem>findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);
	//trazer da Lista da Tabela, Pelo Titulo e Ignore SE é maiuscula e minuscula e Liste todos os titulos com os titulos iguais
	

}
