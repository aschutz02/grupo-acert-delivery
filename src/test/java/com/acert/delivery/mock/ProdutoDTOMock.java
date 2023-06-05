package com.acert.delivery.mock;

import com.acert.delivery.service.produto.dto.ProdutoDTO;

public class ProdutoDTOMock {

    private final ProdutoDTO produtoDTO;

    private ProdutoDTOMock() {
        this.produtoDTO = new ProdutoDTO();
        produtoDTO.setId(1L);
        produtoDTO.setNome("Pizza");
        produtoDTO.setPreco(79.99);
    }

    public static ProdutoDTOMock builder() {
        return new ProdutoDTOMock();
    }

    public ProdutoDTO build() {
        return this.produtoDTO;
    }
}
