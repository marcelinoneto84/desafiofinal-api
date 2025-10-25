package br.com.xpeducacao.marcelinoneto.desafiofinal.pedidos.config;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.xpeducacao.marcelinoneto.desafiofinal.pedidos.model.Pedido;
import br.com.xpeducacao.marcelinoneto.desafiofinal.pedidos.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final PedidoRepository pedidoRepository;

    @Override
    public void run(String... args) throws Exception {
        if (pedidoRepository.count() == 0) {
            log.info("🔄 Inicializando dados de pedidos...");
            
            // Pedido 1: João Silva comprando Smartphone
            Pedido pedido1 = new Pedido();
            pedido1.setClienteId(1L);
            pedido1.setProdutoId(1L);
            pedido1.setQuantidade(1);
            pedido1.setValorTotal(new BigDecimal("899.99"));
            pedido1.setStatus(Pedido.StatusPedido.CONFIRMADO);
            pedido1.setDataPedido(LocalDateTime.now().minusDays(5));
            
            // Pedido 2: Maria Santos comprando Notebook Dell
            Pedido pedido2 = new Pedido();
            pedido2.setClienteId(2L);
            pedido2.setProdutoId(2L);
            pedido2.setQuantidade(1);
            pedido2.setValorTotal(new BigDecimal("2499.99"));
            pedido2.setStatus(Pedido.StatusPedido.ENTREGUE);
            pedido2.setDataPedido(LocalDateTime.now().minusDays(10));
            
            // Pedido 3: Pedro Oliveira comprando Mouse Gamer + Teclado
            Pedido pedido3 = new Pedido();
            pedido3.setClienteId(3L);
            pedido3.setProdutoId(3L);
            pedido3.setQuantidade(2);
            pedido3.setValorTotal(new BigDecimal("299.80"));
            pedido3.setStatus(Pedido.StatusPedido.PENDENTE);
            pedido3.setDataPedido(LocalDateTime.now().minusDays(2));
            
            // Pedido 4: Ana Costa comprando Monitor
            Pedido pedido4 = new Pedido();
            pedido4.setClienteId(4L);
            pedido4.setProdutoId(5L);
            pedido4.setQuantidade(1);
            pedido4.setValorTotal(new BigDecimal("599.99"));
            pedido4.setStatus(Pedido.StatusPedido.CONFIRMADO);
            pedido4.setDataPedido(LocalDateTime.now().minusDays(3));
            
            // Pedido 5: Carlos Ferreira comprando Headset
            Pedido pedido5 = new Pedido();
            pedido5.setClienteId(5L);
            pedido5.setProdutoId(6L);
            pedido5.setQuantidade(1);
            pedido5.setValorTotal(new BigDecimal("199.90"));
            pedido5.setStatus(Pedido.StatusPedido.ENTREGUE);
            pedido5.setDataPedido(LocalDateTime.now().minusDays(7));
            
            // Pedido 6: João Silva comprando Teclado Mecânico  
            Pedido pedido6 = new Pedido();
            pedido6.setClienteId(1L);
            pedido6.setProdutoId(4L);
            pedido6.setQuantidade(1);
            pedido6.setValorTotal(new BigDecimal("299.90"));
            pedido6.setStatus(Pedido.StatusPedido.CANCELADO);
            pedido6.setDataPedido(LocalDateTime.now().minusDays(1));
            
            pedidoRepository.save(pedido1);
            pedidoRepository.save(pedido2);
            pedidoRepository.save(pedido3);
            pedidoRepository.save(pedido4);
            pedidoRepository.save(pedido5);
            pedidoRepository.save(pedido6);
            
            log.info("✅ {} pedidos foram carregados com sucesso!", pedidoRepository.count());
        } else {
            log.info("📊 Base de dados já contém {} pedidos. Pulando inicialização.", pedidoRepository.count());
        }
    }
}