package br.com.xpeducacao.marcelinoneto.desafiofinal.produtos.config;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.xpeducacao.marcelinoneto.desafiofinal.produtos.model.Produto;
import br.com.xpeducacao.marcelinoneto.desafiofinal.produtos.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final ProdutoRepository produtoRepository;

    @Override
    public void run(String... args) throws Exception {
        if (produtoRepository.count() == 0) {
            log.info("🔄 Inicializando dados de produtos...");
            
            Produto produto1 = new Produto();
            produto1.setNome("Smartphone Android");
            produto1.setDescricao("Smartphone Android com 128GB de armazenamento, câmera 48MP e tela 6.1 polegadas");
            produto1.setPreco(new BigDecimal("899.99"));
            produto1.setEstoque(50);
            
            Produto produto2 = new Produto();
            produto2.setNome("Notebook Dell Inspiron");
            produto2.setDescricao("Notebook Dell Inspiron 15 com Intel i5, 8GB RAM, SSD 256GB");
            produto2.setPreco(new BigDecimal("2499.99"));
            produto2.setEstoque(25);
            
            Produto produto3 = new Produto();
            produto3.setNome("Mouse Gamer RGB");
            produto3.setDescricao("Mouse Gamer com LED RGB, DPI ajustável até 12000, ergonômico");
            produto3.setPreco(new BigDecimal("149.90"));
            produto3.setEstoque(100);
            
            Produto produto4 = new Produto();
            produto4.setNome("Teclado Mecânico");
            produto4.setDescricao("Teclado Mecânico RGB com switches blue, layout ABNT2");
            produto4.setPreco(new BigDecimal("299.90"));
            produto4.setEstoque(75);
            
            Produto produto5 = new Produto();
            produto5.setNome("Monitor 24 Full HD");
            produto5.setDescricao("Monitor LED 24 polegadas Full HD, IPS, 75Hz, bordas ultrafinas");
            produto5.setPreco(new BigDecimal("599.99"));
            produto5.setEstoque(30);
            
            Produto produto6 = new Produto();
            produto6.setNome("Headset Gamer");
            produto6.setDescricao("Headset Gamer com microfone destacável, som surround 7.1");
            produto6.setPreco(new BigDecimal("199.90"));
            produto6.setEstoque(60);
            
            produtoRepository.save(produto1);
            produtoRepository.save(produto2);
            produtoRepository.save(produto3);
            produtoRepository.save(produto4);
            produtoRepository.save(produto5);
            produtoRepository.save(produto6);
            
            log.info("✅ {} produtos foram carregados com sucesso!", produtoRepository.count());
        } else {
            log.info("📊 Base de dados já contém {} produtos. Pulando inicialização.", produtoRepository.count());
        }
    }
}