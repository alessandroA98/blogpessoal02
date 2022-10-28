package br.org.generation.BlogPessoal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class BlogPessoalApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogPessoalApplication.class, args);
	}

}
