package br.com.xpeducacao.marcelinoneto.desafiofinal.produtos.repository;

import br.com.xpeducacao.marcelinoneto.desafiofinal.produtos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}