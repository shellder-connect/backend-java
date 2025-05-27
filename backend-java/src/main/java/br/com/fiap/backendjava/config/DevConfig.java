package br.com.fiap.backendjava.config;

import br.com.fiap.backendjava.services.DbService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DbService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    @Transactional
    public boolean instantiateDatabase() {
        if (!"create".equals(strategy)) {
            log.info("Banco de dados já possui registros. Nenhuma alteração feita.");
            return false;
        }

        dbService.instantiateTestDatabase();
        log.info("Banco de dados criado com sucesso e clinica inserida.");
        return true;
    }
}
