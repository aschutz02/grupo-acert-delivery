package com.acert.delivery.service.cliente;

import com.acert.delivery.entity.cliente.Cliente;
import com.acert.delivery.repository.cliente.ClienteRepository;
import com.acert.delivery.service.cliente.dto.ClienteDTO;
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

    public ClienteDTO cadastrarCliente(ClienteDTO clienteDTO) {
        Cliente cliente = deClienteDTOParaCliente(clienteDTO);
        return deClienteParaClienteDTO(clienteRepository.save(cliente));
    }

    public List<ClienteDTO> encontrarTodosOsClientes() {
        return deListaClientesParaListaClientesDTO(clienteRepository.findAll());
    }

    public ClienteDTO encontrarPorNome(String nome) {
        return deClienteParaClienteDTO(clienteRepository.findByNome(nome)
                .orElseThrow(() -> new ClienteNotFoundException(String.format(MENSAGEM_DE_ERRO, nome))));
    }

    public ClienteDTO atualizarCliente(String nome, ClienteDTO clienteDTO) {
        Optional<Cliente> cliente = clienteRepository.findByNome(nome);

        if (clienteExiste(cliente)) {
            Cliente novoCliente = deClienteDTOParaCliente(clienteDTO);
            return deClienteParaClienteDTO(clienteRepository.save(novoCliente));
        } else {
            throw new ClienteNotFoundException(String.format(MENSAGEM_DE_ERRO, nome));
        }
    }

    public void deletarPorNome(String nome) {
        Optional<Cliente> cliente = clienteRepository.findByNome(nome);

        if (clienteExiste(cliente)) {
            clienteRepository.deleteClienteByNome(nome);
        } else {
            throw new ClienteNotFoundException(String.format(MENSAGEM_DE_ERRO, nome));
        }
    }

    private boolean clienteExiste(Optional<Cliente> optionalCliente) {
        return optionalCliente.isPresent();
    }

}
