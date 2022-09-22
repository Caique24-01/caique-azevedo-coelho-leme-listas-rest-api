package br.com.aceleragep.listas.restapi.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
public class OpenAPIConfig {

	@Bean 
	public OpenAPI springShopOpenAPI() {
		OpenAPI openAPI = new OpenAPI();
		
		Info info = new Info();
		info.title("Rest API Listas G&P ");
		info.version("v0.0.1");
		info.description("<h2>Rest API de Listas desenvolvida para avaliação back-end</h2>");
		openAPI.info(info);
		
		openAPI.addTagsItem(new Tag().name("Lista").description("Gerencia as listas do sistema"));
		openAPI.addTagsItem(new Tag().name("Item").description("Gerencia os itens do sistema"));
		
		return openAPI;
	}
}
