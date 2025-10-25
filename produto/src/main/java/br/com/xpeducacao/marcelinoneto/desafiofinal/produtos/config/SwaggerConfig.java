package br.com.xpeducacao.marcelinoneto.desafiofinal.produtos.config;

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
                        .title("📦 Produto Service API")
                        .description("**Sistema de Vendas Online - Microserviço de Produtos**\n\n" +
                                "Este microserviço gerencia o catálogo completo de produtos, oferecendo:\n" +
                                "- ✅ CRUD completo de produtos\n" +
                                "- 💰 Controle de preços com BigDecimal\n" +
                                "- 📦 Gestão de estoque\n" +
                                "- 🎯 Validação de dados\n\n" +
                                "**Funcionalidades:**\n" +
                                "- Cadastro de produtos com preço e estoque\n" +
                                "- Controle de disponibilidade\n" +
                                "- Endpoints otimizados para alta performance\n" +
                                "- Dados pré-populados de produtos tecnológicos\n\n" +
                                "**Tecnologias:**\n" +
                                "- Spring Boot 2.7.18\n" +
                                "- Spring Data JPA\n" +
                                "- H2 Database\n" +
                                "- Lombok\n" +
                                "- BigDecimal para precisão monetária")
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