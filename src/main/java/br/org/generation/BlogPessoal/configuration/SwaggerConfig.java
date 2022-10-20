package br.org.generation.BlogPessoal.configuration;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
public class SwaggerConfig {
	
@Bean
public OpenAPI springBlogPessoalOpenAPI() {
	return new OpenAPI().info(new Info().title("Projeto Blog Pessoal")
			.description("criacao de um blog pessoal")
					.version("v1.0")
				.license(new License()
					.name("alessandro")
					.url("http://blogpessoal.com/"))
				.contact(new Contact()
					.name("alessandro")
					.url("https://github.com/alessandroa98")
					.email("alessandro.assuncao98@gmail.com")))
				.externalDocs(new ExternalDocumentation()
					.description("github")
					.url("https://github.com/alessandroa98"));
}

@Bean
public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() {
	
	return openApi -> {
		openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
			
		ApiResponses apiResponses = operation.getResponses();
		
		apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
		apiResponses.addApiResponse("201", createApiResponse("objeto Persistido!"));
		apiResponses.addApiResponse("204", createApiResponse("Objeto Excluido!"));
		apiResponses.addApiResponse("400", createApiResponse("Erro na requisição!"));
		apiResponses.addApiResponse("401", createApiResponse("Acesso não Autorizado!"));
		apiResponses.addApiResponse("404", createApiResponse("Objeto Não Encontrado!"));
		apiResponses.addApiResponse("500", createApiResponse("Erro Na Aplicação!"));
		}));
	};
	
}

private ApiResponse createApiResponse(String message) {
	return new ApiResponse().description(message);
}


}
