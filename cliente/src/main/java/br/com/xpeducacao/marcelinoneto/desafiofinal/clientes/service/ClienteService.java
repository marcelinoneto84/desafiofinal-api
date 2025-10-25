package br.com.xpeducacao.marcelinoneto.desafiofinal.clientes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import br.com.xpeducacao.marcelinoneto.desafiofinal.clientes.model.Cliente;
import br.com.xpeducacao.marcelinoneto.desafiofinal.clientes.repository.ClienteRepository;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Optional<Cliente> findById(Long id) {
        return repository.findById(id);
    }

    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Cliente> buscarPorNome(String nome) {
        return repository.findByNome(nome);
    }

    public long contarClientes() {
        return repository.count();
    }
}
