package br.com.xpeducacao.marcelinoneto.desafiofinal.pedidos.service;

import br.com.xpeducacao.marcelinoneto.desafiofinal.pedidos.dto.ClienteDto;
import br.com.xpeducacao.marcelinoneto.desafiofinal.pedidos.dto.PedidoDetalheDto;
import br.com.xpeducacao.marcelinoneto.desafiofinal.pedidos.dto.ProdutoDto;
import br.com.xpeducacao.marcelinoneto.desafiofinal.pedidos.model.Pedido;
import br.com.xpeducacao.marcelinoneto.desafiofinal.pedidos.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ExternalServiceLocator serviceLocator;

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> findById(Long id) {
        return pedidoRepository.findById(id);
    }

    public List<Pedido> findByClienteId(Long clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }

    public Pedido save(Pedido pedido) {
        if (pedido.getDataPedido() == null) {
            pedido.setDataPedido(LocalDateTime.now());
        }
        if (pedido.getStatus() == null) {
            pedido.setStatus(Pedido.StatusPedido.PENDENTE);
        }
        return pedidoRepository.save(pedido);
    }

    public void deleteById(Long id) {
        pedidoRepository.deleteById(id);
    }
    
    /**
     * Busca detalhes completos do pedido usando Builder Pattern e Service Locator Pattern
     * Combina dados do pedido com informações de cliente e produto de outros microserviços
     */
    public Optional<PedidoDetalheDto> findDetalhesById(Long id) {
        log.info("🔍 Buscando detalhes completos do pedido ID: {}", id);
        
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);
        
        if (!pedidoOpt.isPresent()) {
            log.warn("⚠️ Pedido não encontrado para ID: {}", id);
            return Optional.empty();
        }
        
        Pedido pedido = pedidoOpt.get();
        
        // Usando Service Locator Pattern para buscar dados externos
        ClienteDto cliente = serviceLocator.findClienteById(pedido.getClienteId());
        ProdutoDto produto = serviceLocator.findProdutoById(pedido.getProdutoId());
        
        // Usando Builder Pattern para construir o DTO de detalhes
        PedidoDetalheDto detalhes = PedidoDetalheDto.builder()
                .id(pedido.getId())
                .quantidade(pedido.getQuantidade())
                .valorTotal(pedido.getValorTotal())
                .dataPedido(pedido.getDataPedido())
                .status(pedido.getStatus())
                .cliente(cliente)
                .produto(produto)
                .valorUnitario(calcularValorUnitario(pedido.getValorTotal(), pedido.getQuantidade()))
                .statusDescricao(getStatusDescricao(pedido.getStatus()))
                .build();
        
        log.info("✅ Detalhes do pedido {} montados com sucesso", id);
        return Optional.of(detalhes);
    }
    
    /**
     * Calcula o valor unitário do produto no pedido
     */
    private BigDecimal calcularValorUnitario(BigDecimal valorTotal, Integer quantidade) {
        if (valorTotal == null || quantidade == null || quantidade == 0) {
            return BigDecimal.ZERO;
        }
        return valorTotal.divide(BigDecimal.valueOf(quantidade), 2, RoundingMode.HALF_UP);
    }
    
    /**
     * Retorna descrição amigável do status do pedido
     */
    private String getStatusDescricao(Pedido.StatusPedido status) {
        if (status == null) return "Status indefinido";
        
        switch (status) {
            case PENDENTE:
                return "Aguardando confirmação";
            case CONFIRMADO:
                return "Pedido confirmado e em preparação";
            case ENTREGUE:
                return "Pedido entregue com sucesso";
            case CANCELADO:
                return "Pedido cancelado";
            default:
                return "Status desconhecido";
        }
    }
}