package com.acert.delivery.service.produto;

import com.acert.delivery.entity.produto.Produto;
import com.acert.delivery.repository.produto.ProdutoRepository;
import com.acert.delivery.service.produto.dto.ProdutoDTO;
import com.acert.delivery.service.produto.exception.ProdutoNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return deProdutoParaProdutoDTO(buscarProdutoPeloNome(nome)
                .orElseThrow(() -> new ProdutoNotFoundException(String.format(MENSAGEM_DE_ERRO, nome))));
    }

    public ProdutoDTO atualizarProduto(String nome, ProdutoDTO produtoDTO) {
        Optional<Produto> produto = buscarProdutoPeloNome(nome);

        if (produtoExiste(produto)) {
            Produto novoProduto = deProdutoDTOParaProduto(produtoDTO);
            return deProdutoParaProdutoDTO(produtoRepository.save(novoProduto));
        } else {
            throw new ProdutoNotFoundException(String.format(MENSAGEM_DE_ERRO, nome));
        }
    }

    public void deletarPeloNome(String nome) {
        Optional<Produto> produto = buscarProdutoPeloNome(nome);

        if (produtoExiste(produto)) {
            produtoRepository.deleteProdutoByNome(nome);
        } else {
            throw new ProdutoNotFoundException(String.format(MENSAGEM_DE_ERRO, nome));
        }
    }

    private Optional<Produto> buscarProdutoPeloNome(String nome) {
        return produtoRepository.findByNome(nome);
    }

    private boolean produtoExiste(Optional<Produto> optionalProduto) {
        return optionalProduto.isPresent();
    }
}
