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

    public ClienteResponseDTO encontrarPorNome(String nome) {
        return deClienteParaClienteDTO(buscarClientePorNome(nome)
                .orElseThrow(() -> new ClienteNotFoundException(String.format(MENSAGEM_DE_ERRO, nome))));
    }

    public ClienteResponseDTO atualizarCliente(String nome, ClienteRequestDTO clienteRequestDTO) {
        Optional<Cliente> cliente = buscarClientePorNome(nome);

        if (clienteExiste(cliente)) {
            Cliente novoCliente = deClienteDTOParaCliente(clienteRequestDTO);
            return deClienteParaClienteDTO(clienteRepository.save(novoCliente));
        } else {
            throw new ClienteNotFoundException(String.format(MENSAGEM_DE_ERRO, nome));
        }
    }

    public void deletarPorNome(String nome) {
        Optional<Cliente> cliente = buscarClientePorNome(nome);

        if (clienteExiste(cliente)) {
            clienteRepository.deleteClienteByNome(nome);
        } else {
            throw new ClienteNotFoundException(String.format(MENSAGEM_DE_ERRO, nome));
        }
    }

    private Optional<Cliente> buscarClientePorNome(String nome) {
        return clienteRepository.findByNome(nome);
    }

    private boolean clienteExiste(Optional<Cliente> optionalCliente) {
        return optionalCliente.isPresent();
    }

}
