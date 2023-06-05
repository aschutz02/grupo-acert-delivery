package com.acert.delivery.service.pedido.mapper;

import com.acert.delivery.entity.pedido.Pedido;
import com.acert.delivery.service.pedido.dto.PedidoRequestDTO;
import com.acert.delivery.service.pedido.dto.PedidoResponseDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PedidoMapper {

    public static Pedido dePedidoDTOParaPedido(PedidoRequestDTO pedidoRequestDTO) {
        return Optional.ofNullable(pedidoRequestDTO)
                .map(pedido -> Pedido.builder()
                        .id(pedido.getId())
                        .produtoId(pedido.getProdutoId())
                        .entregaId(pedido.getEntregaId())
                        .clienteId(pedido.getClienteId())
                        .build())
                .orElse(null);
    }

    public static List<PedidoResponseDTO> deListaPedidosParaListaPedidosDTO(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(PedidoMapper::dePedidoParaPedidoDTO)
                .collect(Collectors.toList());
    }

    public static PedidoResponseDTO dePedidoParaPedidoDTO(Pedido pedido) {
        return Optional.ofNullable(pedido)
                .map(entidadePedido -> PedidoResponseDTO.builder()
                        .id(entidadePedido.getId())
                        .build())
                .orElse(null);
    }
}
