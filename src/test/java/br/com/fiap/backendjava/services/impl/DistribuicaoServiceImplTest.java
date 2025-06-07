package br.com.fiap.backendjava.services.impl;

import br.com.fiap.backendjava.domains.Distribuicao;
import br.com.fiap.backendjava.gateways.repositories.DistribuicaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DistribuicaoServiceImplTest {

    @InjectMocks
    private DistribuicaoServiceImpl distribuicaoService;

    @Mock
    private DistribuicaoRepository distribuicaoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveBuscarDistribuicaoPorId() {
        Distribuicao distribuicao = new Distribuicao();
        distribuicao.setId(1);
        when(distribuicaoRepository.findById(1)).thenReturn(Optional.of(distribuicao));

        Distribuicao resultado = distribuicaoService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        verify(distribuicaoRepository).findById(1);
    }

    @Test
    void deveLancarExcecaoQuandoDistribuicaoNaoEncontrada() {
        when(distribuicaoRepository.findById(1)).thenReturn(Optional.empty());

        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> distribuicaoService.buscarPorId(1));
        assertEquals("Distribuição não encontrada com ID: 1", excecao.getMessage());
    }

    @Test
    void deveListarTodasDistribuicoes() {
        List<Distribuicao> lista = List.of(new Distribuicao(), new Distribuicao());
        when(distribuicaoRepository.findAll()).thenReturn(lista);

        List<Distribuicao> resultado = distribuicaoService.buscarTodos();

        assertEquals(2, resultado.size());
        verify(distribuicaoRepository).findAll();
    }

    @Test
    void deveAtualizarDistribuicao() {
        Distribuicao existente = new Distribuicao();
        existente.setId(1);
        Distribuicao nova = new Distribuicao();
        nova.setQtdDestinada(5);
        nova.setDataDistribuicao(LocalDate.of(2024, 1, 1));

        when(distribuicaoRepository.findById(1)).thenReturn(Optional.of(existente));
        when(distribuicaoRepository.save(existente)).thenReturn(existente);

        Distribuicao resultado = distribuicaoService.atualizar(1, nova);

        assertEquals(5, resultado.getQtdDestinada());
        assertEquals(LocalDate.of(2024, 1, 1), resultado.getDataDistribuicao());
        verify(distribuicaoRepository).save(existente);
    }

    @Test
    void deveDeletarDistribuicao() {
        Distribuicao existente = new Distribuicao();
        existente.setId(1);
        when(distribuicaoRepository.findById(1)).thenReturn(Optional.of(existente));
        doNothing().when(distribuicaoRepository).delete(existente);

        Boolean resultado = distribuicaoService.deletar(1);

        assertTrue(resultado);
        verify(distribuicaoRepository).delete(existente);
    }
}
