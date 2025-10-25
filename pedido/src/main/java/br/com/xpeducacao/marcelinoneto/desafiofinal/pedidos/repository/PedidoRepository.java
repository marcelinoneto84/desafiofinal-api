package br.com.xpeducacao.marcelinoneto.desafiofinal.pedidos.repository;

import br.com.xpeducacao.marcelinoneto.desafiofinal.pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByClienteId(Long clienteId);
}