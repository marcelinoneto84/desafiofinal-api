package br.com.xpeducacao.marcelinoneto.desafiofinal.pedidos.controller;

import br.com.xpeducacao.marcelinoneto.desafiofinal.pedidos.dto.PedidoDetalheDto;
import br.com.xpeducacao.marcelinoneto.desafiofinal.pedidos.model.Pedido;
import br.com.xpeducacao.marcelinoneto.desafiofinal.pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService service;

    @GetMapping
    public List<Pedido> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{clienteId}")
    public List<Pedido> findByClienteId(@PathVariable Long clienteId) {
        return service.findByClienteId(clienteId);
    }

    @GetMapping("/{id}/detalhes")
    public ResponseEntity<PedidoDetalheDto> findDetalhesById(@PathVariable Long id) {
        return service.findDetalhesById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pedido save(@RequestBody Pedido pedido) {
        return service.save(pedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> update(@PathVariable Long id, @RequestBody Pedido pedido) {
        return service.findById(id)
                .map(existingPedido -> {
                    pedido.setId(id);
                    return ResponseEntity.ok(service.save(pedido));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}