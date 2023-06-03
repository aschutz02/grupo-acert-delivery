package com.acert.delivery.service.cliente;

import com.acert.delivery.entity.cliente.Cliente;
import com.acert.delivery.repository.cliente.ClienteRepository;
import com.acert.delivery.service.cliente.dto.ClienteRequestDTO;
import com.acert.delivery.service.cliente.dto.ClienteResponseDTO;
import com.acert.delivery.service.cliente.exception.ClienteNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public ClienteResponseDTO encontrarPorEmail(String email) {
        return deClienteParaClienteDTO(buscarClientePorEmail(email)
                .orElseThrow(() -> new ClienteNotFoundException(String.format(MENSAGEM_DE_ERRO, email))));
    }

    public ClienteResponseDTO atualizarCliente(String email, ClienteRequestDTO clienteRequestDTO) {
        Optional<Cliente> cliente = buscarClientePorEmail(email);

        if (clienteExiste(cliente)) {
            Cliente novoCliente = deClienteDTOParaCliente(clienteRequestDTO);
            return deClienteParaClienteDTO(clienteRepository.save(novoCliente));
        } else {
            throw new ClienteNotFoundException(String.format(MENSAGEM_DE_ERRO, email));
        }
    }

    public void deletarPorEmail(String email) {
        Optional<Cliente> cliente = buscarClientePorEmail(email);

        if (clienteExiste(cliente)) {
            clienteRepository.deleteClienteByEmail(email);
        } else {
            throw new ClienteNotFoundException(String.format(MENSAGEM_DE_ERRO, email));
        }
    }

    private Optional<Cliente> buscarClientePorEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    private boolean clienteExiste(Optional<Cliente> optionalCliente) {
        return optionalCliente.isPresent();
    }

}
