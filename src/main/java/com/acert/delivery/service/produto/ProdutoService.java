package com.acert.delivery.service.produto;

import com.acert.delivery.entity.produto.Produto;
import com.acert.delivery.repository.produto.ProdutoRepository;
import com.acert.delivery.service.produto.dto.ProdutoDTO;
import com.acert.delivery.service.produto.exception.ProdutoNotFoundException;
import com.acert.delivery.service.produto.mapper.ProdutoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.acert.delivery.service.produto.mapper.ProdutoMapper.deListaProdutosParaListaProdutosDTO;
import static com.acert.delivery.service.produto.mapper.ProdutoMapper.deProdutoParaProdutoDTO;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private static final String MENSAGEM_DE_ERRO = "Produto %s não encontrado.";

    public ProdutoDTO cadastrarProduto(ProdutoDTO produtoDTO) {
        Produto produto = ProdutoMapper.deProdutoDTOParaProduto(produtoDTO);
        return deProdutoParaProdutoDTO(produtoRepository.save(produto));
    }

    public List<ProdutoDTO> encontrarTodosOsProdutos() {
        return deListaProdutosParaListaProdutosDTO(produtoRepository.findAll());
    }

    public ProdutoDTO encontrarPorNome(String nome) {
        return deProdutoParaProdutoDTO(buscarProdutoPorNome(nome)
                .orElseThrow(() -> new ProdutoNotFoundException(String.format(MENSAGEM_DE_ERRO, nome))));
    }

    public ProdutoDTO atualizarProduto(String nome, ProdutoDTO produtoDTO) {
        Optional<Produto> produto = buscarProdutoPorNome(nome);

        if (produtoExiste(produto)) {
            Produto novoProduto = ProdutoMapper.deProdutoDTOParaProduto(produtoDTO);
            return deProdutoParaProdutoDTO(produtoRepository.save(novoProduto));
        } else {
            throw new ProdutoNotFoundException(String.format(MENSAGEM_DE_ERRO, nome));
        }
    }

    public void deletarPorNome(String nome) {
        Optional<Produto> produto = buscarProdutoPorNome(nome);

        if (produtoExiste(produto)) {
            produtoRepository.deleteProdutoByNome(nome);
        } else {
            throw new ProdutoNotFoundException(String.format(MENSAGEM_DE_ERRO, nome));
        }
    }

    private Optional<Produto> buscarProdutoPorNome(String nome) {
        return produtoRepository.findByNome(nome);
    }

    private boolean produtoExiste(Optional<Produto> optionalProduto) {
        return optionalProduto.isPresent();
    }
}
