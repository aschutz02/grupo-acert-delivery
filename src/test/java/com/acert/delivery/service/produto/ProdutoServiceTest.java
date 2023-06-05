package com.acert.delivery.service.produto;

import com.acert.delivery.entity.produto.Produto;
import com.acert.delivery.mock.ProdutoDTOMock;
import com.acert.delivery.repository.produto.ProdutoRepository;
import com.acert.delivery.service.produto.dto.ProdutoDTO;
import com.acert.delivery.service.produto.exception.ProdutoNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.acert.delivery.service.produto.mapper.ProdutoMapper.deProdutoDTOParaProduto;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @Test
    void cadastrarProduto_sucesso() {

        ProdutoDTO produtoDTO = ProdutoDTOMock.builder().build();
        when(produtoRepository.save(deProdutoDTOParaProduto(produtoDTO))).thenReturn(deProdutoDTOParaProduto(produtoDTO));

        ProdutoDTO response = produtoService.cadastrarProduto(produtoDTO);

        verify(produtoRepository).save(deProdutoDTOParaProduto(produtoDTO));
        assertNotNull(response);
    }

    @Test
    void encontrarTodosOsProdutos_sucesso() {

        List<Produto> produtos = new ArrayList<>();

        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Pizza");
        produto.setPreco(79.99);

        produtos.add(produto);

        when(produtoRepository.findAll()).thenReturn((produtos));

        List<ProdutoDTO> response = produtoService.encontrarTodosOsProdutos();

        verify(produtoRepository).findAll();
        assertFalse(response.isEmpty());
    }

    @Test
    void encontrarTodosOsProdutos_listaVazia() {

        when(produtoRepository.findAll()).thenReturn(new ArrayList<>());

        List<ProdutoDTO> response = produtoService.encontrarTodosOsProdutos();

        verify(produtoRepository).findAll();
        assertTrue(response.isEmpty());
    }


    @Test
    void encontrarPeloNome_sucesso() {

        ProdutoDTO produtoDTO = ProdutoDTOMock.builder().build();

        String nome = "Pizza";

        when(produtoRepository.findByNome(nome)).thenReturn(Optional.of(deProdutoDTOParaProduto(produtoDTO)));

        ProdutoDTO response = produtoService.encontrarPeloNome(nome);

        verify(produtoRepository).findByNome(nome);
        assertNotNull(response);
    }

    @Test
    void encontrarPeloNome_produtoNotFoundException() {

        String nome = "Pizza";

        when(produtoRepository.findByNome(nome)).thenReturn(Optional.empty());

        ProdutoNotFoundException exception = assertThrows(
                ProdutoNotFoundException.class,
                () -> produtoService.encontrarPeloNome(nome));

        assertEquals("Produto Pizza não encontrado.", exception.getMessage());
    }

    @Test
    void atualizarProduto_sucesso() {

        ProdutoDTO produtoDTO = ProdutoDTOMock.builder().build();

        String nome = "Pizza";

        when(produtoRepository.findByNome(nome)).thenReturn(Optional.of(deProdutoDTOParaProduto(produtoDTO)));
        when(produtoRepository.save(deProdutoDTOParaProduto(produtoDTO))).thenReturn(deProdutoDTOParaProduto(produtoDTO));

        ProdutoDTO response = produtoService.atualizarProduto(nome, produtoDTO);

        verify(produtoRepository).findByNome(nome);
        verify(produtoRepository).save(deProdutoDTOParaProduto(produtoDTO));
        assertNotNull(response);
    }

    @Test
    void atualizarProduto_produtoNotFoundException() {

        ProdutoDTO produtoDTO = ProdutoDTOMock.builder().build();

        String nome = "Pizza";

        when(produtoRepository.findByNome(nome)).thenReturn(Optional.empty());

        ProdutoNotFoundException exception = assertThrows(
                ProdutoNotFoundException.class,
                () -> produtoService.atualizarProduto(nome, produtoDTO));

        assertEquals("Produto Pizza não encontrado.", exception.getMessage());

        verify(produtoRepository).findByNome(nome);
        verifyNoMoreInteractions(produtoRepository);
    }

    @Test
    void deletarPeloNome_sucesso() {

        ProdutoDTO produtoDTO = ProdutoDTOMock.builder().build();

        String nome = "Pizza";

        when(produtoRepository.findByNome(nome)).thenReturn(Optional.of(deProdutoDTOParaProduto(produtoDTO)));

        produtoService.deletarPeloNome(nome);

        verify(produtoRepository).deleteProdutoByNome(nome);
    }

    @Test
    void deletarPeloNome_produtoNotFoundException() {

        String nome = "Pizza";

        when(produtoRepository.findByNome(nome)).thenReturn(Optional.empty());

        ProdutoNotFoundException exception = assertThrows(
                ProdutoNotFoundException.class,
                () -> produtoService.deletarPeloNome(nome));

        assertEquals("Produto Pizza não encontrado.", exception.getMessage());

        verify(produtoRepository).findByNome(nome);
        verifyNoMoreInteractions(produtoRepository);
    }
}