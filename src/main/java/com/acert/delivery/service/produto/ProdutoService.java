package com.acert.delivery.service.produto;

import com.acert.delivery.entity.produto.Produto;
import com.acert.delivery.repository.produto.ProdutoRepository;
import com.acert.delivery.service.produto.dto.ProdutoDTO;
import com.acert.delivery.service.produto.mapper.ProdutoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoDTO cadastrarProduto(ProdutoDTO produtoDTO) {
        Produto produto = ProdutoMapper.deProdutoDTOParaProduto(produtoDTO);
        return ProdutoMapper.deProdutoParaProdutoDTO(produtoRepository.save(produto));
    }

    public List<ProdutoDTO> encontrarTodosOsProdutos() {
        return ProdutoMapper.deListaProdutosParaListaProdutosDTO(produtoRepository.findAll());
    }

    public ProdutoDTO encontrarPorNome(String nome) {
        return ProdutoMapper.deProdutoParaProdutoDTO(produtoRepository.findByNome(nome)
                .orElseThrow(() -> new ProdutoNotFoundException(String.format("Produto %s não encontrado.", nome))));
    }

    public ProdutoDTO atualizarProduto(String nome, ProdutoDTO produtoDTO) {
        Optional<Produto> produto = produtoRepository.findByNome(nome);

        if (produtoExiste(produto)) {
            Produto novoProduto = ProdutoMapper.deProdutoDTOParaProduto(produtoDTO);
            return ProdutoMapper.deProdutoParaProdutoDTO(produtoRepository.save(novoProduto));
        } else {
            throw new ProdutoNotFoundException(String.format("Produto %s não encontrado.", nome));
        }
    }

    public void deletarPorNome(String nome) {
        Optional<Produto> produto = produtoRepository.findByNome(nome);

        if (produtoExiste(produto)) {
            produtoRepository.deleteProdutoByNome(nome);
        } else {
            throw new ProdutoNotFoundException(String.format("Produto %s não encontrado.", nome));
        }
    }

    private boolean produtoExiste(Optional<Produto> optionalProduto) {
        return optionalProduto.isPresent();
    }
}
