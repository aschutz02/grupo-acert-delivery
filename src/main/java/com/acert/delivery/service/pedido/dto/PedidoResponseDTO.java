package com.acert.delivery.service.pedido.dto;

import com.acert.delivery.service.cliente.dto.ClienteResponseDTO;
import com.acert.delivery.service.entrega.dto.EntregaDTO;
import com.acert.delivery.service.produto.dto.ProdutoDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ApiModel
public class PedidoResponseDTO {

    @ApiModelProperty(value = "Id", example = "1")
    private Long id;

    @ApiModelProperty(value = "Produto")
    private ProdutoDTO produto;

    @ApiModelProperty(value = "Cliente")
    private ClienteResponseDTO cliente;

    @ApiModelProperty(value = "Entrega")
    private EntregaDTO entrega;

    @ApiModelProperty(value = "Valor Total", example = "96.00")
    private Double valorTotal;
}
