package com.acert.delivery.service.pedido.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PedidoDTO {

    private Long id;
    private List<Long> produtosId;
    private Long clienteId;
    private Long entregaId;
}
