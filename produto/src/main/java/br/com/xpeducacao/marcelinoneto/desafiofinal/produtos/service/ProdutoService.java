package br.com.xpeducacao.marcelinoneto.desafiofinal.produtos.service;

import br.com.xpeducacao.marcelinoneto.desafiofinal.produtos.model.Produto;
import br.com.xpeducacao.marcelinoneto.desafiofinal.produtos.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> findById(Long id) {
        return produtoRepository.findById(id);
    }

    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void deleteById(Long id) {
        produtoRepository.deleteById(id);
    }
}