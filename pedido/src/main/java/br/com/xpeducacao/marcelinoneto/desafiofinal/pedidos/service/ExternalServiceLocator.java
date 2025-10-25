package br.com.xpeducacao.marcelinoneto.desafiofinal.pedidos.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.xpeducacao.marcelinoneto.desafiofinal.pedidos.dto.ClienteDto;
import br.com.xpeducacao.marcelinoneto.desafiofinal.pedidos.dto.ProdutoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service Locator Pattern - Localiza e consome serviços externos
 * Este padrão centraliza a lógica de localização de serviços, 
 * facilitando a manutenção e permitindo mudanças de configuração
 * sem alterar o código cliente.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ExternalServiceLocator {
    
    private final RestTemplate restTemplate;
    
    @Value("${app.services.cliente.url:http://localhost:8081}")
    private String clienteServiceUrl;
    
    @Value("${app.services.produto.url:http://localhost:8082}")
    private String produtoServiceUrl;
    
    /**
     * Localiza e consulta o serviço de Cliente
     */
    public ClienteDto findClienteById(Long clienteId) {
        try {
            String url = clienteServiceUrl + "/clientes/" + clienteId;
            log.info("🔍 Consultando cliente no serviço: {}", url);
            
            ClienteDto cliente = restTemplate.getForObject(url, ClienteDto.class);
            
            if (cliente != null) {
                log.info("✅ Cliente encontrado: {} (ID: {})", cliente.getNome(), cliente.getId());
            } else {
                log.warn("⚠️ Cliente não encontrado para ID: {}", clienteId);
            }
            
            return cliente;
        } catch (Exception e) {
            log.error("❌ Erro ao consultar cliente ID {}: {}", clienteId, e.getMessage());
            return createClienteFallback(clienteId);
        }
    }
    
    /**
     * Localiza e consulta o serviço de Produto
     */
    public ProdutoDto findProdutoById(Long produtoId) {
        try {
            String url = produtoServiceUrl + "/produtos/" + produtoId;
            log.info("🔍 Consultando produto no serviço: {}", url);
            
            ProdutoDto produto = restTemplate.getForObject(url, ProdutoDto.class);
            
            if (produto != null) {
                log.info("✅ Produto encontrado: {} (ID: {})", produto.getNome(), produto.getId());
            } else {
                log.warn("⚠️ Produto não encontrado para ID: {}", produtoId);
            }
            
            return produto;
        } catch (Exception e) {
            log.error("❌ Erro ao consultar produto ID {}: {}", produtoId, e.getMessage());
            return createProdutoFallback(produtoId);
        }
    }
    
    /**
     * Fallback para quando o serviço de cliente não estiver disponível
     */
    private ClienteDto createClienteFallback(Long clienteId) {
        return ClienteDto.builder()
                .id(clienteId)
                .nome("Cliente Indisponível")
                .email("servico-indisponivel@sistema.com")
                .documento("00000000000")
                .score(0)
                .build();
    }
    
    /**
     * Fallback para quando o serviço de produto não estiver disponível
     */
    private ProdutoDto createProdutoFallback(Long produtoId) {
        return ProdutoDto.builder()
                .id(produtoId)
                .nome("Produto Indisponível")
                .descricao("Serviço de produtos temporariamente indisponível")
                .preco(java.math.BigDecimal.ZERO)
                .estoque(0)
                .build();
    }
}