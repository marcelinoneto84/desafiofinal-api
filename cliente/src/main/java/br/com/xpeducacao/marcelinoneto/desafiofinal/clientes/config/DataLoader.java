package br.com.xpeducacao.marcelinoneto.desafiofinal.clientes.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.xpeducacao.marcelinoneto.desafiofinal.clientes.model.Cliente;
import br.com.xpeducacao.marcelinoneto.desafiofinal.clientes.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final ClienteRepository clienteRepository;

    @Override
    public void run(String... args) throws Exception {
        if (clienteRepository.count() == 0) {
            log.info("🔄 Inicializando dados de clientes...");
            
            Cliente cliente1 = new Cliente();
            cliente1.setNome("João Silva");
            cliente1.setEmail("joao.silva@email.com");
            cliente1.setDocumento("12345678901");
            cliente1.setScore(850);
            
            Cliente cliente2 = new Cliente();
            cliente2.setNome("Maria Santos");
            cliente2.setEmail("maria.santos@email.com");
            cliente2.setDocumento("98765432100");
            cliente2.setScore(750);
            
            Cliente cliente3 = new Cliente();
            cliente3.setNome("Pedro Oliveira");
            cliente3.setEmail("pedro.oliveira@email.com");
            cliente3.setDocumento("45678912300");
            cliente3.setScore(920);
            
            Cliente cliente4 = new Cliente();
            cliente4.setNome("Ana Costa");
            cliente4.setEmail("ana.costa@email.com");
            cliente4.setDocumento("78912345600");
            cliente4.setScore(680);
            
            Cliente cliente5 = new Cliente();
            cliente5.setNome("Carlos Ferreira");
            cliente5.setEmail("carlos.ferreira@email.com");
            cliente5.setDocumento("32165498700");
            cliente5.setScore(800);
            
            clienteRepository.save(cliente1);
            clienteRepository.save(cliente2);
            clienteRepository.save(cliente3);
            clienteRepository.save(cliente4);
            clienteRepository.save(cliente5);
            
            log.info("✅ {} clientes foram carregados com sucesso!", clienteRepository.count());
        } else {
            log.info("📊 Base de dados já contém {} clientes. Pulando inicialização.", clienteRepository.count());
        }
    }
}