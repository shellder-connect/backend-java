package br.com.fiap.backendjava.services.impl;

import br.com.fiap.backendjava.domains.Abrigo;
import br.com.fiap.backendjava.gateways.dtos.abrigo.AbrigoCreateDTO;
import br.com.fiap.backendjava.gateways.repositories.AbrigoRepository;
import br.com.fiap.backendjava.mappers.AbrigoMapper;
import br.com.fiap.backendjava.services.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AbrigoServiceImplTest {

    @Mock
    private AbrigoRepository repository;

    @Mock
    private AbrigoMapper mapper;

    @Mock
    private ChatClient chatClient;

    @InjectMocks
    private AbrigoServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarAbrigoComSucesso() {
        AbrigoCreateDTO dto = new AbrigoCreateDTO(20, 100, "Abrigo Central");
        Abrigo abrigo = new Abrigo();
        abrigo.setId(1);
        abrigo.setDescricao("Abrigo Central");

        when(mapper.toEntityFromCreateDTO(dto)).thenReturn(abrigo);
        when(repository.save(abrigo)).thenReturn(abrigo);

        Abrigo resultado = service.criar(dto);

        assertNotNull(resultado);
        assertEquals("Abrigo Central", resultado.getDescricao());
        verify(repository).save(abrigo);
    }

    @Test
    void deveBuscarAbrigoPorIdComSucesso() {
        Abrigo abrigo = new Abrigo();
        abrigo.setId(1);
        abrigo.setDescricao("Abrigo Centro");

        when(repository.findById(1)).thenReturn(Optional.of(abrigo));

        Abrigo resultado = service.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals("Abrigo Centro", resultado.getDescricao());
        verify(repository).findById(1);
    }

    @Test
    void deveLancarExcecaoQuandoAbrigoNaoEncontrado() {
        when(repository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> service.buscarPorId(99));
        verify(repository).findById(99);
    }
}
