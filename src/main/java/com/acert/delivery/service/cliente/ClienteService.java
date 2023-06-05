package com.acert.delivery.service.cliente;

import com.acert.delivery.entity.cliente.Cliente;
import com.acert.delivery.repository.cliente.ClienteRepository;
import com.acert.delivery.service.cliente.dto.ClienteRequestDTO;
import com.acert.delivery.service.cliente.dto.ClienteResponseDTO;
import com.acert.delivery.service.cliente.exception.ClienteNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.acert.delivery.service.cliente.mapper.ClienteMapper.*;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private static final String MENSAGEM_DE_ERRO = "Cliente %s não encontrado.";

    public ClienteResponseDTO cadastrarCliente(ClienteRequestDTO clienteRequestDTO) {
        Cliente cliente = deClienteDTOParaCliente(clienteRequestDTO);
        return deClienteParaClienteDTO(clienteRepository.save(cliente));
    }

    public List<ClienteResponseDTO> encontrarTodosOsClientes() {
        return deListaClientesParaListaClientesDTO(clienteRepository.findAll());
    }

    public ClienteResponseDTO encontrarPeloEmail(String email) {
        return deClienteParaClienteDTO(buscarClientePeloEmail(email));
    }

    public ClienteResponseDTO encontrarPeloId(Long id) {
        return deClienteParaClienteDTO(clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(String.format(MENSAGEM_DE_ERRO, id))));
    }

    public ClienteResponseDTO atualizarCliente(String email, ClienteRequestDTO clienteRequestDTO) {
        buscarClientePeloEmail(email);

        Cliente novoCliente = deClienteDTOParaCliente(clienteRequestDTO);
        return deClienteParaClienteDTO(clienteRepository.save(novoCliente));
    }

    public void deletarPeloEmail(String email) {
        buscarClientePeloEmail(email);

        clienteRepository.deleteClienteByEmail(email);
    }

    private Cliente buscarClientePeloEmail(String email) {
        return clienteRepository.findByEmail(email)
                .orElseThrow(() -> new ClienteNotFoundException(String.format(MENSAGEM_DE_ERRO, email)));
    }
}
