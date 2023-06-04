package com.acert.delivery.service.pedido.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ApiModel
public class PedidoDTO {

    @ApiModelProperty(value = "Id", example = "1")
    private Long id;

    @ApiModelProperty(value = "Produtos Id", example = "[1, 2]")
    @Valid
    private List<Long> produtosId;

    @ApiModelProperty(value = "Cliente Id", example = "1")
    @NotNull
    private Long clienteId;

    @ApiModelProperty(value = "Entrega Id", example = "1")
    private Long entregaId;
}
