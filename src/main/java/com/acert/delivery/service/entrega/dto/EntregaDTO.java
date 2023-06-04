package com.acert.delivery.service.entrega.dto;

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
public class EntregaDTO {

    @ApiModelProperty(value = "Id", example = "1")
    private Long id;

    @ApiModelProperty(value = "Cidade", example = "Araçatuba")
    @NotNull
    private String cidade;

    @ApiModelProperty(value = "Estado", example = "São Paulo")
    @NotNull
    private String estado;

    @ApiModelProperty(value = "Bairro", example = "Limoeiro")
    private String bairro;

    @ApiModelProperty(value = "CEP", example = "91000-00")
    @NotNull
    private String cep;

    @ApiModelProperty(value = "Número", example = "328")
    @NotNull
    private String numero;

    @ApiModelProperty(value = "Complemento", example = "Apartamento 103")
    private String complemento;
}
