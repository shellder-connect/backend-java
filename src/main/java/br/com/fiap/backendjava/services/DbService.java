package br.com.fiap.backendjava.services;

import br.com.fiap.backendjava.domains.Abrigo;
import br.com.fiap.backendjava.domains.Categoria;
import br.com.fiap.backendjava.domains.Distribuicao;
import br.com.fiap.backendjava.domains.Doacao;
import br.com.fiap.backendjava.domains.Endereco;
import br.com.fiap.backendjava.domains.Feedback;
import br.com.fiap.backendjava.domains.User;
import br.com.fiap.backendjava.domains.enums.Role;
import br.com.fiap.backendjava.gateways.repositories.AbrigoRepository;
import br.com.fiap.backendjava.gateways.repositories.CategoriaRepository;
import br.com.fiap.backendjava.gateways.repositories.DistribuicaoRepository;
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
    private final DistribuicaoRepository distribuicaoRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder pe;

    @Transactional
    public void instantiateTestDatabase() {

        Endereco endereco1 = new Endereco(null, "Rua das Flores", "123", "Centro", "São Paulo", "SP", "01234-567", "Casa Amarela");
        Endereco endereco2 = new Endereco(null, "Avenida Brasil", "456", "Jardim das Rosas", "São Paulo", "SP", "01234-567", "Apartamento 101");
        Endereco endereco3 = new Endereco(null, "Rua do Sol", "789", "Vila Nova", "São Paulo", "SP", "01234-567", "Escritório");
        Endereco endereco4 = new Endereco(null, "Rua da Lua", "321", "Bairro Alegre", "São Paulo", "SP", "01234-567", "Loja 5");
        Endereco endereco5 = new Endereco(null, "Rua das Estrelas", "654", "Vila Esperança", "São Paulo", "SP", "01234-567", "Galpão");
        Endereco endereco6 = new Endereco(null, "Avenida das Nações", "789", "Centro", "São Paulo", "SP", "04567-890", "Prédio Comercial");
        Endereco endereco7 = new Endereco(null, "Rua da Liberdade", "321", "Liberdade", "São Paulo", "SP", "01503-000", "Fundos");
        Endereco endereco8 = new Endereco(null, "Travessa do Sol", "159", "Jardim Luz", "São Paulo", "SP", "02345-678", "Casa 2");
        Endereco endereco9 = new Endereco(null, "Alameda das Palmeiras", "753", "Morumbi", "São Paulo", "SP", "05678-910", "Bloco B");
        Endereco endereco10 = new Endereco(null, "Rua Aurora Boreal", "951", "Bela Vista", "São Paulo", "SP", "01310-000", "Cobertura");
        enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2, endereco3, endereco4, endereco5, endereco6, endereco7, endereco8, endereco9, endereco10));

        User admin = new User(null, "Patricia Naomi", "admin@email.com", pe.encode("123456"), Role.ADMIN, "11961393029", endereco1, LocalDate.of(1995, 5, 28), "382344935", true);
        User voluntario = new User(null, "João Silva", "voluntario@email.com", pe.encode("123456"), Role.VOLUNTARIO, "11999999999", endereco2, LocalDate.of(1995, 5, 28), "123456789", true);
        User voluntario2 = new User(null, "Ana Pereira", "voluntario2@email.com", pe.encode("123456"), Role.VOLUNTARIO, "11988888888", endereco7, LocalDate.of(1998, 1, 2), "987654321", true);
        User voluntario3 = new User(null, "Carlos Almeida", "voluntario3@email.com", pe.encode("123456"), Role.VOLUNTARIO, "11977777777", endereco8, LocalDate.of(1995, 5, 28), "123123123", true);
        User empresaParceira = new User(null, "Empresa X", "empresa@email.com", pe.encode("123456"), Role.EMPRESAPARCEIRA, "11888888888", endereco3, LocalDate.of(1995, 5, 28), "987654321", true);
        User empresaParceira2 = new User(null, "Instituto Esperança", "contato@esperanca.org", pe.encode("123456"), Role.EMPRESAPARCEIRA, "11888888889", endereco4, LocalDate.of(1990, 8, 15), "998877665", true);
        User empresaParceira3 = new User(null, "Associação Solidária", "admin@solidaria.com", pe.encode("123456"), Role.EMPRESAPARCEIRA, "11888888900", endereco5, LocalDate.of(1985, 12, 5), "887766554", true);
        User solicitante = new User(null, "Maria Oliveira", "solicitante@email.com", pe.encode("123456"), Role.SOLICITANTE, "11777777777", null, LocalDate.of(1995, 5, 28), "111222333", true);
        User solicitante2 = new User(null, "Carlos Souza", "solicitante2@email.com", pe.encode("123456"), Role.SOLICITANTE, "11777777778", null, LocalDate.of(1995, 5, 28), "222333444", true);
        User solicitante3 = new User(null, "Ana Costa", "ana.costa@email.com", pe.encode("123456"), Role.SOLICITANTE, "11777777779", null, LocalDate.of(1996, 2, 14), "333444555", true);
        User solicitante4 = new User(null, "Bruno Lima", "bruno.lima@email.com", pe.encode("123456"), Role.SOLICITANTE, "11777777780", null, LocalDate.of(1994, 7, 22), "444555666", true);
        User solicitante5 = new User(null, "Fernanda Silva", "fernanda.silva@email.com", pe.encode("123456"), Role.SOLICITANTE, "11777777781", null, LocalDate.of(1992, 11, 5), "555666777", true);
        User solicitante6 = new User(null, "Lucas Martins", "lucas.martins@email.com", pe.encode("123456"), Role.SOLICITANTE, "11777777782", null, LocalDate.of(1990, 3, 18), "666777888", true);
        User solicitante7 = new User(null, "Juliana Rocha", "juliana.rocha@email.com", pe.encode("123456"), Role.SOLICITANTE, "11777777783", null, LocalDate.of(1993, 9, 9), "777888999", true);
        User profissionalSaude = new User(null, "Dr. Ricardo", "saude@email.com", pe.encode("123456"), Role.PROFISSIONALSAUDE, "11666666666", endereco6, LocalDate.of(1995, 5, 28), "444555666", true);
        User profissionalSaude2 = new User(null, "Dra. Fernanda", "saude2@email.com", pe.encode("123456"), Role.PROFISSIONALSAUDE, "11666666667", endereco9, LocalDate.of(1990, 3, 18), "555666777", true);
        User profissionalSaude3 = new User(null, "Dr. Lucas", "saude3@email.com", pe.encode("123456"), Role.PROFISSIONALSAUDE, "11666666668", endereco10, LocalDate.of(1992, 11, 5), "666777888", true);
        userRepository.saveAll(Arrays.asList(admin, voluntario2, voluntario, voluntario3, empresaParceira2, empresaParceira3, profissionalSaude2, profissionalSaude3, empresaParceira, solicitante, profissionalSaude, solicitante2, solicitante3, solicitante4, solicitante5, solicitante6, solicitante7));

        Abrigo abrigo1 = new Abrigo(null, 500, 150, "Abrigo Esperança");
        Abrigo abrigo2 = new Abrigo(null, 300, 100, "Abrigo Luz do Sol");
        Abrigo abrigo3 = new Abrigo(null, 400, 120, "Abrigo Sorriso Feliz");
        abrigoRepository.saveAll(Arrays.asList(abrigo1, abrigo2, abrigo3));

        Categoria categoria1 = new Categoria(null, "Vestuário");
        Categoria categoria2 = new Categoria(null, "Alimentos");
        Categoria categoria3 = new Categoria(null, "Higiene Pessoal");
        Categoria categoria4 = new Categoria(null, "Brinquedos");
        Categoria categoria5 = new Categoria(null, "Móveis");
        categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2, categoria3, categoria4, categoria5));

        Doacao doacao1 = new Doacao(null, abrigo1, "Roupas Infantis Tamanho 8", categoria1, 50);
        Doacao doacao2 = new Doacao(null, abrigo2, "Cesta Básica", categoria2, 100);
        Doacao doacao3 = new Doacao(null, abrigo3, "Kit de Higiene", categoria3, 30);
        Doacao doacao4 = new Doacao(null, abrigo1, "Livro de Colorir", categoria4, 20);
        Doacao doacao5 = new Doacao(null, abrigo2, "Sofá Usado", categoria5, 1);
        doacaoRepository.saveAll(Arrays.asList(doacao1, doacao2, doacao3, doacao4, doacao5));

        Feedback feedback1 = new Feedback(null, 5, "Excelente psiquiatra, me ajudou muito!", LocalDate.now().atStartOfDay(), solicitante, profissionalSaude);
        Feedback feedback2 = new Feedback(null, 4, "Muito atenciosa e profissional.", LocalDate.now().atStartOfDay(), solicitante2, profissionalSaude2);
        Feedback feedback3 = new Feedback(null, 5, "Ótimo atendimento, recomendo!", LocalDate.now().atStartOfDay(), solicitante3, profissionalSaude3);
        Feedback feedback4 = new Feedback(null, 3, "Atendimento bom, mas poderia melhorar.", LocalDate.now().atStartOfDay(), solicitante4, profissionalSaude);
        Feedback feedback5 = new Feedback(null, 2, "Não gostei do atendimento, achei muito rápido.", LocalDate.now().atStartOfDay(), solicitante5, profissionalSaude2);
        Feedback feedback6 = new Feedback(null, 4, "Bom atendimento, mas a espera foi longa.", LocalDate.now().atStartOfDay(), solicitante6, profissionalSaude3);
        Feedback feedback7 = new Feedback(null, 5, "Excelente profissional, muito atencioso!", LocalDate.now().atStartOfDay(), solicitante7, profissionalSaude);
        feedbackRepository.saveAll(Arrays.asList(feedback1, feedback2, feedback3, feedback4, feedback5, feedback6, feedback7));

        Distribuicao distribuicao1 = new Distribuicao(null, doacao1, 2, LocalDate.now().minusDays(5), solicitante3);
        Distribuicao distribuicao2 = new Distribuicao(null, doacao2, 1, LocalDate.now().minusDays(4), solicitante4);
        Distribuicao distribuicao3 = new Distribuicao(null, doacao3, 3, LocalDate.now().minusDays(3), solicitante5);
        Distribuicao distribuicao4 = new Distribuicao(null, doacao4, 1, LocalDate.now().minusDays(2), solicitante6);
        Distribuicao distribuicao5 = new Distribuicao(null, doacao5, 2, LocalDate.now().minusDays(1), solicitante7);
        distribuicaoRepository.saveAll(Arrays.asList(distribuicao1, distribuicao2, distribuicao3, distribuicao4, distribuicao5));
        log.info("Banco de dados criado com sucesso!");
    }
}
