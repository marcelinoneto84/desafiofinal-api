package br.com.xpeducacao.marcelinoneto.desafiofinal.pedidos.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.xpeducacao.marcelinoneto.desafiofinal.pedidos.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDetalheDto {
    private Long id;
    private Integer quantidade;
    private BigDecimal valorTotal;
    private LocalDateTime dataPedido;
    private Pedido.StatusPedido status;
    
    // Dados completos do cliente
    private ClienteDto cliente;
    
    // Dados completos do produto
    private ProdutoDto produto;
    
    // Informações calculadas
    private BigDecimal valorUnitario;
    private String statusDescricao;
}