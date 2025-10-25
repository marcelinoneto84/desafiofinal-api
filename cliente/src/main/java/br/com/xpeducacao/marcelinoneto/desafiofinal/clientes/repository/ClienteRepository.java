package br.com.xpeducacao.marcelinoneto.desafiofinal.clientes.repository;

import br.com.xpeducacao.marcelinoneto.desafiofinal.clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNome(String nome);
}
