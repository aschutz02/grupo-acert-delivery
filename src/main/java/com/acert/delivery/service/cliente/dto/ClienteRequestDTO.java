package com.acert.delivery.service.cliente.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClienteRequestDTO {

    private Long id;
    private String email;
    private String senha;
}
