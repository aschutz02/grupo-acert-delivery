package com.acert.delivery.service.entrega.mapper;

import com.acert.delivery.entity.entrega.Entrega;
import com.acert.delivery.service.entrega.dto.EntregaDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EntregaMapper {

    public static Entrega deEntregaDTOParaEntrega(EntregaDTO entregaDTO) {
        return Optional.ofNullable(entregaDTO)
                .map(entrega -> Entrega.builder()
                        .id(entrega.getId())
                        .cidade(entrega.getCidade())
                        .estado(entrega.getEstado())
                        .bairro(entrega.getBairro())
                        .cep(entrega.getCep())
                        .numero(entrega.getNumero())
                        .complemento(entrega.getComplemento())
                        .build())
                .orElse(null);
    }

    public static List<EntregaDTO> deListaEntregasParaListaEntregasDTO(List<Entrega> entregas) {
        return entregas.stream()
                .map(EntregaMapper::deEntregaParaEntregaDTO)
                .collect(Collectors.toList());
    }

    public static EntregaDTO deEntregaParaEntregaDTO(Entrega entrega) {
        return Optional.ofNullable(entrega)
                .map(entidadeEntrega -> EntregaDTO.builder()
                        .id(entidadeEntrega.getId())
                        .cidade(entidadeEntrega.getCidade())
                        .estado(entidadeEntrega.getEstado())
                        .bairro(entidadeEntrega.getBairro())
                        .cep(entidadeEntrega.getCep())
                        .numero(entidadeEntrega.getNumero())
                        .complemento(entidadeEntrega.getComplemento())
                        .build())
                .orElse(null);
    }
}
