package br.com.xpeducacao.marcelinoneto.desafiofinal.clientes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("🧑‍💼 Cliente Service API")
                        .description("**Sistema de Vendas Online - Microserviço de Clientes**\n\n" +
                                "Este microserviço é responsável pelo gerenciamento completo de clientes, incluindo:\n" +
                                "- ✅ CRUD completo de clientes\n" +
                                "- 🔍 Busca por nome\n" +
                                "- 📊 Contagem de clientes\n" +
                                "- 🎯 Validação de dados\n\n" +
                                "**Funcionalidades:**\n" +
                                "- Cadastro de clientes com score de crédito\n" +
                                "- Busca flexível por nome\n" +
                                "- Endpoints otimizados para microserviços\n" +
                                "- Dados pré-populados para demonstração\n\n" +
                                "**Tecnologias:**\n" +
                                "- Spring Boot 2.7.18\n" +
                                "- Spring Data JPA\n" +
                                "- H2 Database\n" +
                                "- Lombok")
                        .version("2.0")
                        .contact(new Contact()
                                .name("Marcelino Neto")
                                .email("marcelino.neto@estudante.xpe.edu.br")
                                .url("https://github.com/marcelinoneto84"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")));
    }
}