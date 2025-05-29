package br.com.fiap.backendjava.services;

import br.com.fiap.backendjava.domains.Endereco;
import br.com.fiap.backendjava.domains.User;
import br.com.fiap.backendjava.domains.enums.Role;
import br.com.fiap.backendjava.gateways.repositories.AbrigoRepository;
import br.com.fiap.backendjava.gateways.repositories.CategoriaRepository;
import br.com.fiap.backendjava.gateways.repositories.DoacaoRepository;
import br.com.fiap.backendjava.gateways.repositories.EnderecoRepository;
import br.com.fiap.backendjava.gateways.repositories.FeedbackRepository;
import br.com.fiap.backendjava.gateways.repositories.RegistroEventoRespository;
import br.com.fiap.backendjava.gateways.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Slf4j
public class DbService {

    private final AbrigoRepository abrigoRepository;
    private final CategoriaRepository categoriaRepository;
    private final DoacaoRepository doacaoRepository;
    private final EnderecoRepository enderecoRepository;
    private final FeedbackRepository feedbackRepository;
    private final RegistroEventoRespository registroEventoRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder pe;

    @Transactional
    public void instantiateTestDatabase() {
        log.info("Banco de dados criado com sucesso!");

        Endereco endereco1 = new Endereco(null, "Rua das Flores", "123", "Centro", "São Paulo", "SP", "01234-567", "Casa Amarela");
        Endereco endereco2 = new Endereco(null, "Avenida Brasil", "456", "Jardim das Rosas", "São Paulo", "SP", "01234-567", "Apartamento 101");
        Endereco endereco3 = new Endereco(null, "Rua do Sol", "789", "Vila Nova", "São Paulo", "SP", "01234-567", "Escritório");
        Endereco endereco4 = new Endereco(null, "Rua da Lua", "321", "Bairro Alegre", "São Paulo", "SP", "01234-567", "Loja 5");
        Endereco endereco5 = new Endereco(null, "Rua das Estrelas", "654", "Vila Esperança", "São Paulo", "SP", "01234-567", "Galpão");
        enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2, endereco3, endereco4, endereco5));

        User admin = new User(null, "Patricia Naomi", "admin@email.com", pe.encode("senha123"), Role.ADMIN, "11961393029", endereco1, LocalDate.of(1995, 5, 28), "382344935", true);
        User voluntario = new User(null, "João Silva", "joao@email.com", pe.encode("senha123"), Role.VOLUNTARIO, "11999999999", endereco2, LocalDate.of(1995, 5, 28), "123456789", true);
        User empresaParceira = new User(null, "Empresa X", "empresa@email.com", pe.encode("senha123"), Role.EMPRESAPARCEIRA, "11888888888", endereco1, LocalDate.of(1995, 5, 28), "987654321", true);
        User solicitante = new User(null, "Maria Oliveira", "maria@email.com", pe.encode("senha123"), Role.SOLICITANTE, "11777777777", endereco2, LocalDate.of(1995, 5, 28), "111222333", true);
        User profissionalSaude = new User(null, "Dr. Ricardo", "ricardo@email.com", pe.encode("senha123"), Role.PROFISSIONALSAUDE, "11666666666", endereco1, LocalDate.of(1995, 5, 28), "444555666", true);
        userRepository.saveAll(Arrays.asList(admin, voluntario, empresaParceira, solicitante, profissionalSaude));

        log.info("Banco criado com sucesso!");
    }
}
