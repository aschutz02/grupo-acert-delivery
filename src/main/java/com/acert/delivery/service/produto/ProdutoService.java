package com.acert.delivery.service.produto;

import com.acert.delivery.entity.produto.Produto;
import com.acert.delivery.repository.produto.ProdutoRepository;
import com.acert.delivery.service.produto.dto.ProdutoDTO;
import com.acert.delivery.service.produto.exception.ProdutoNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.acert.delivery.service.produto.mapper.ProdutoMapper.*;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private static final String MENSAGEM_DE_ERRO = "Produto %s não encontrado.";

    public ProdutoDTO cadastrarProduto(ProdutoDTO produtoDTO) {
        Produto produto = deProdutoDTOParaProduto(produtoDTO);
        return deProdutoParaProdutoDTO(produtoRepository.save(produto));
    }

    public List<ProdutoDTO> encontrarTodosOsProdutos() {
        return deListaProdutosParaListaProdutosDTO(produtoRepository.findAll());
    }

    public ProdutoDTO encontrarPeloNome(String nome) {
        return deProdutoParaProdutoDTO(buscarProdutoPeloNome(nome));
    }

    public ProdutoDTO encontrarPeloId(Long id) {
        return deProdutoParaProdutoDTO(produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException(String.format(MENSAGEM_DE_ERRO, id))));
    }

    public ProdutoDTO atualizarProduto(String nome, ProdutoDTO produtoDTO) {
        buscarProdutoPeloNome(nome);

        Produto novoProduto = deProdutoDTOParaProduto(produtoDTO);
        return deProdutoParaProdutoDTO(produtoRepository.save(novoProduto));
    }

    public void deletarPeloNome(String nome) {
        buscarProdutoPeloNome(nome);

        produtoRepository.deleteProdutoByNome(nome);
    }

    private Produto buscarProdutoPeloNome(String nome) {
        return produtoRepository.findByNome(nome)
                .orElseThrow(() -> new ProdutoNotFoundException(String.format(MENSAGEM_DE_ERRO, nome)));
    }
}
