package com.acert.delivery.service.cliente.mapper;

import com.acert.delivery.entity.cliente.Cliente;
import com.acert.delivery.service.cliente.dto.ClienteRequestDTO;
import com.acert.delivery.service.cliente.dto.ClienteResponseDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ClienteMapper {

    public static Cliente deClienteDTOParaCliente(ClienteRequestDTO clienteRequestDTO) {
        return Optional.ofNullable(clienteRequestDTO)
                .map(cliente -> Cliente.builder()
                        .id(cliente.getId())
                        .email(cliente.getEmail())
                        .senha(cliente.getSenha())
                        .build())
                .orElse(null);
    }

    public static List<ClienteResponseDTO> deListaClientesParaListaClientesDTO(List<Cliente> clientes) {
        return clientes.stream()
                .map(ClienteMapper::deClienteParaClienteDTO)
                .collect(Collectors.toList());
    }

    public static ClienteResponseDTO deClienteParaClienteDTO(Cliente cliente) {
        return Optional.ofNullable(cliente)
                .map(entidadeCliente -> ClienteResponseDTO.builder()
                        .id(entidadeCliente.getId())
                        .email(entidadeCliente.getEmail())
                        .senha(entidadeCliente.getSenha())
                        .build())
                .orElse(null);
    }
}
