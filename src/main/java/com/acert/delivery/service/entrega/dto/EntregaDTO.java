package com.acert.delivery.service.entrega.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EntregaDTO {

    private Long id;
    private String cidade;
    private String estado;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
}
