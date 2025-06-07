package br.com.fiap.backendjava.services.impl;

import br.com.fiap.backendjava.domains.Categoria;
import br.com.fiap.backendjava.gateways.repositories.CategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CategoriaServiceImplTest {

    @InjectMocks
    private CategoriaServiceImpl categoriaService;

    @Mock
    private CategoriaRepository categoriaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarCategoria() {
        Categoria categoria = new Categoria(null, "Alimentos");
        Categoria categoriaSalva = new Categoria(1, "Alimentos");

        when(categoriaRepository.save(categoria)).thenReturn(categoriaSalva);

        Categoria resultado = categoriaService.criar(categoria);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Alimentos", resultado.getDescricao());
        verify(categoriaRepository).save(categoria);
    }

    @Test
    void deveBuscarCategoriaPorId() {
        Categoria categoria = new Categoria(1, "Higiene");

        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoria));

        Categoria resultado = categoriaService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals("Higiene", resultado.getDescricao());
        verify(categoriaRepository).findById(1);
    }

    @Test
    void deveLancarExcecaoQuandoCategoriaNaoEncontrada() {
        when(categoriaRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException excecao = assertThrows(RuntimeException.class, () -> categoriaService.buscarPorId(1));
        assertEquals("Categoria não encontrada com id: 1", excecao.getMessage());
    }

    @Test
    void deveListarTodasCategorias() {
        List<Categoria> lista = List.of(
                new Categoria(1, "Alimentos"),
                new Categoria(2, "Roupas")
        );

        when(categoriaRepository.findAll()).thenReturn(lista);

        List<Categoria> resultado = categoriaService.buscarTodos();

        assertEquals(2, resultado.size());
        verify(categoriaRepository).findAll();
    }

    @Test
    void deveAtualizarCategoria() {
        Categoria categoriaExistente = new Categoria(1, "Antiga");
        Categoria categoriaAtualizada = new Categoria(null, "Nova");

        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoriaExistente));
        when(categoriaRepository.save(any(Categoria.class))).thenAnswer(i -> i.getArgument(0));

        Categoria resultado = categoriaService.atualizar(1, categoriaAtualizada);

        assertEquals("Nova", resultado.getDescricao());
        verify(categoriaRepository).save(categoriaExistente);
    }

    @Test
    void deveDeletarCategoria() {
        Categoria categoria = new Categoria(1, "Testar");

        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoria));
        doNothing().when(categoriaRepository).delete(categoria);

        Boolean resultado = categoriaService.deletar(1);

        assertTrue(resultado);
        verify(categoriaRepository).delete(categoria);
    }
}

