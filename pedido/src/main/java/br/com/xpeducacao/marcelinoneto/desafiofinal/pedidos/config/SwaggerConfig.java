package br.com.xpeducacao.marcelinoneto.desafiofinal.pedidos.config;

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
                        .title("🛒 Pedido Service API")
                        .description("**Sistema de Vendas Online - Microserviço de Pedidos**\n\n" +
                                "Este microserviço é o núcleo do sistema, responsável por:\n" +
                                "- ✅ CRUD completo de pedidos\n" +
                                "- 🔗 Integração com outros microserviços\n" +
                                "- 📊 Detalhes completos com dados agregados\n" +
                                "- 🎯 Controle de status do pedido\n\n" +
                                "**Funcionalidades Avançadas:**\n" +
                                "- Consulta de detalhes completos (pedido + cliente + produto)\n" +
                                "- Service Locator Pattern para integração\n" +
                                "- Builder Pattern para construção de DTOs\n" +
                                "- Fallback automático em caso de falha de serviços\n" +
                                "- Status tracking (PENDENTE → CONFIRMADO → ENTREGUE)\n\n" +
                                "**Padrões de Projeto:**\n" +
                                "- Service Locator Pattern\n" +
                                "- Builder Pattern (Lombok)\n" +
                                "- Repository Pattern\n" +
                                "- DTO Pattern\n\n" +
                                "**Tecnologias:**\n" +
                                "- Spring Boot 2.7.18\n" +
                                "- Spring Data JPA\n" +
                                "- RestTemplate (integração HTTP)\n" +
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