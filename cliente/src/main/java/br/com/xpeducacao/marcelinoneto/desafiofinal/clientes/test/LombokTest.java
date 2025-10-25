package br.com.xpeducacao.marcelinoneto.desafiofinal.clientes.test;

import br.com.xpeducacao.marcelinoneto.desafiofinal.clientes.model.Cliente;

/**
 * Classe de teste para verificar se o Lombok está funcionando
 */
public class LombokTest {
    
    public static void main(String[] args) {
        // Testando @Builder
        Cliente cliente = Cliente.builder()
                .nome("João Silva")
                .email("joao@email.com")
                .documento("12345678901")
                .score(850)
                .build();
        
        // Testando @Data (getters/setters)
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("Email: " + cliente.getEmail());
        
        cliente.setNome("João Santos");
        System.out.println("Nome alterado: " + cliente.getNome());
        
        // Testando toString (incluído no @Data)
        System.out.println("Cliente: " + cliente.toString());
        
        System.out.println("✅ Lombok funcionando corretamente!");
    }
}