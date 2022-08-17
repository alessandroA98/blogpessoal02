package br.org.generation.BlogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.org.generation.BlogPessoal.model.Usuario;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {
		
		usuarioRepository.deleteAll();
		
		usuarioRepository.save(new Usuario(0L, "joao da silva", "foto", "email1@1.com","12345678" ));
		usuarioRepository.save(new Usuario(0L, "joao da silva 2", "foto2", "email2@2.com","12345678" ));
		usuarioRepository.save(new Usuario(0L, "joao da silva 3", "foto3", "email3@3.com","12345678" ));
	}
	
	@Test
	@DisplayName("Retorna 1 usuario")
	public void deveRetornarUmUsuario() {
		
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("email3@3.com");
		assertTrue(usuario.get().getUsuario().equals("email3@3.com"));
		
	}
	
	@Test
	@DisplayName("retorna 3 usuarios")
	public void retornaTresUsuarios() {
		
		List <Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("joao da silva"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("joao da silva 2"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("joao da silva 3"));
		
	}
	
}
