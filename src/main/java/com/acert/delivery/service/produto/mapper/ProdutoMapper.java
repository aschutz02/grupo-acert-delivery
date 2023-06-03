package com.acert.delivery.service.produto.mapper;

import com.acert.delivery.entity.produto.Produto;
import com.acert.delivery.service.produto.dto.ProdutoDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ProdutoMapper {


    public static Produto deProdutoDTOParaProduto(ProdutoDTO produtoDTO) {
        return Optional.ofNullable(produtoDTO)
                .map(produto -> Produto.builder()
                        .id(produto.getId())
                        .nome(produto.getNome())
                        .preco(produto.getPreco())
                        .build())
                .orElse(null);
    }

    public static List<ProdutoDTO> deListaProdutosParaListaProdutosDTO(List<Produto> produtos) {
        return produtos.stream()
                .map(ProdutoMapper::deProdutoParaProdutoDTO)
                .collect(Collectors.toList());
    }

    public static ProdutoDTO deProdutoParaProdutoDTO(Produto produto) {
        return Optional.ofNullable(produto)
                .map(entidadeProduto -> ProdutoDTO.builder()
                        .id(entidadeProduto.getId())
                        .nome(entidadeProduto.getNome())
                        .preco(entidadeProduto.getPreco())
                        .build())
                .orElse(null);
    }
}
