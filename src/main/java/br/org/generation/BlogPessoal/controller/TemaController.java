package br.org.generation.BlogPessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.org.generation.BlogPessoal.model.Tema;
import br.org.generation.BlogPessoal.repository.TemaRepository;

@RestController
@RequestMapping("/temas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemaController {
	
	@Autowired
	private TemaRepository temaRepository;
	
	@GetMapping
	public ResponseEntity<List<Tema>> GetAll(){
		return ResponseEntity.ok(temaRepository.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Tema> GetById(@PathVariable Long id) {
	// Resposta chamando o Tabela	
		return temaRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		//Metodo procure na lista de Postagem, e busque por Id, mapeie se existe o Id, se não responda Não encontrado
	}	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Tema>> getByDescricao(@PathVariable String descricao) {
		
		return ResponseEntity.ok(temaRepository.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	@PostMapping
	public ResponseEntity<Tema> post(@Valid @RequestBody Tema tema) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(temaRepository.save(tema));
		
	}
	@PutMapping
	public ResponseEntity<Tema> put(@Valid @RequestBody Tema tema){
		
		return temaRepository.findById(tema.getId()).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.OK).body(temaRepository.save(tema))); 
		
	}
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		
		Optional<Tema>postagem = temaRepository.findById(id);
		
		if(postagem.isEmpty()) {
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			
		}
		temaRepository.deleteById(id);
		
	}
	
	
	
	

}
