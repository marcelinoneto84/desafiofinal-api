package br.com.xpeducacao.marcelinoneto.desafiofinal.pedidos.model;

import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long clienteId;
    private Long produtoId;
    private Integer quantidade;
    private BigDecimal valorTotal;
    private LocalDateTime dataPedido;
    
    @Enumerated(EnumType.STRING)
    private StatusPedido status;
    
    public enum StatusPedido {
        PENDENTE, CONFIRMADO, ENTREGUE, CANCELADO
    }
}