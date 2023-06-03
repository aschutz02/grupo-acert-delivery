package com.acert.delivery.service.produto.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProdutoDTO {

    private Long id;
    private String nome;
    private Double preco;
}
