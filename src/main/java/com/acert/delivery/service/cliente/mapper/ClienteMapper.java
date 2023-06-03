package com.acert.delivery.service.cliente.mapper;

import com.acert.delivery.entity.cliente.Cliente;
import com.acert.delivery.service.cliente.dto.ClienteDTO;
import com.acert.delivery.service.entrega.mapper.EntregaMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClienteMapper {

    public static Cliente deClienteDTOParaCliente(ClienteDTO clienteDTO) {
        return Optional.ofNullable(clienteDTO)
                .map(cliente -> Cliente.builder()
                        .id(cliente.getId())
                        .email(cliente.getEmail())
                        .senha(cliente.getSenha())
                        .build())
                .orElse(null);
    }

    public static List<ClienteDTO> deListaClientesParaListaClientesDTO(List<Cliente> clientes) {
        return clientes.stream()
                .map(ClienteMapper::deClienteParaClienteDTO)
                .collect(Collectors.toList());
    }

    public static ClienteDTO deClienteParaClienteDTO(Cliente cliente) {
        return Optional.ofNullable(cliente)
                .map(entidadeCliente -> ClienteDTO.builder()
                        .id(entidadeCliente.getId())
                        .email(entidadeCliente.getEmail())
                        .senha(entidadeCliente.getSenha())
                        .build())
                .orElse(null);
    }
}
