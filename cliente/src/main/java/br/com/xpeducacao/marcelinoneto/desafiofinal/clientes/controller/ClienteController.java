package br.com.xpeducacao.marcelinoneto.desafiofinal.clientes.controller;

import br.com.xpeducacao.marcelinoneto.desafiofinal.clientes.model.Cliente;
import br.com.xpeducacao.marcelinoneto.desafiofinal.clientes.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@Tag(name = "👤 Clientes", description = "Endpoints para gerenciamento de clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "📋 Listar todos os clientes", 
               description = "Retorna uma lista com todos os clientes cadastrados no sistema")
    public List<Cliente> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "🔍 Buscar cliente por ID", 
               description = "Busca um cliente específico pelo seu identificador único")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cliente save(@RequestBody Cliente cliente) {
        return service.save(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente cliente) {
        return service.findById(id)
                .map(existingCliente -> {
                    cliente.setId(id);
                    return ResponseEntity.ok(service.save(cliente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public List<Cliente> buscarPorNome(@PathVariable String nome) {
        return service.buscarPorNome(nome);
    }

    @GetMapping("/contar")
    public long contarClientes() {
        return service.contarClientes();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
