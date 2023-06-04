package com.acert.delivery.service.produto.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ApiModel
public class ProdutoDTO {

    @ApiModelProperty(value = "Id", example = "1")
    private Long id;

    @ApiModelProperty(value = "Nome", example = "Pizza")
    @NotNull
    private String nome;

    @ApiModelProperty(value = "Preço", example = "79,99")
    @NotNull
    private Double preco;
}
