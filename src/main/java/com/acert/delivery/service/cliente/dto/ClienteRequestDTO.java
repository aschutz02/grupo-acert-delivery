package com.acert.delivery.service.cliente.dto;

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
public class ClienteRequestDTO {

    @ApiModelProperty(value = "Id", example = "1")
    private Long id;

    @NotNull
    @ApiModelProperty(value = "Email", example = "arthur@acert.com")
    private String email;

    @NotNull
    @ApiModelProperty(value = "Senha", example = "1234")
    private String senha;
}
