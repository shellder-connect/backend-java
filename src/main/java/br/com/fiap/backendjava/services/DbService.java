package br.com.fiap.backendjava.services;

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
import org.springframework.stereotype.Service;

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
    //private final PasswordEncoder pe;

    @Transactional
    public void instantiateTestDatabase() {
        log.info("Banco de dados criado com sucesso!");
    }
}
