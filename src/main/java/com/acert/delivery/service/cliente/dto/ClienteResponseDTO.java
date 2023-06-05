package com.acert.delivery.service.cliente.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ApiModel
public class ClienteResponseDTO {

    @ApiModelProperty(value = "Id", example = "1")
    private Long id;

    @ApiModelProperty(value = "Email", example = "arthur@acert.com")
    private String email;

    @JsonIgnore
    @ApiModelProperty(value = "Senha", example = "1234")
    private String senha;
}
