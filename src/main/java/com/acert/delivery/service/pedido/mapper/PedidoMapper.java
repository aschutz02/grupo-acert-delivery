package com.acert.delivery.service.pedido.mapper;

import com.acert.delivery.entity.pedido.Pedido;
import com.acert.delivery.service.pedido.dto.PedidoDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PedidoMapper {

    public static Pedido dePedidoDTOParaPedido(PedidoDTO pedidoDTO) {
        return Optional.ofNullable(pedidoDTO)
                .map(pedido -> Pedido.builder()
                        .id(pedido.getId())
                        .produtosId(pedido.getProdutosId())
                        .entregaId(pedido.getEntregaId())
                        .clienteId(pedido.getClienteId())
                        .build())
                .orElse(null);
    }

    public static List<PedidoDTO> deListaPedidosParaListaPedidosDTO(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(PedidoMapper::dePedidoParaPedidoDTO)
                .collect(Collectors.toList());
    }

    public static PedidoDTO dePedidoParaPedidoDTO(Pedido pedido) {
        return Optional.ofNullable(pedido)
                .map(entidadePedido -> PedidoDTO.builder()
                        .id(entidadePedido.getId())
                        .produtosId(entidadePedido.getProdutosId())
                        .entregaId(entidadePedido.getEntregaId())
                        .clienteId(entidadePedido.getClienteId())
                        .build())
                .orElse(null);
    }

}
