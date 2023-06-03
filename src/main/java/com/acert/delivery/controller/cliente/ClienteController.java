package com.acert.delivery.controller.cliente;

import com.acert.delivery.service.cliente.ClienteService;
import com.acert.delivery.service.cliente.dto.ClienteRequestDTO;
import com.acert.delivery.service.cliente.dto.ClienteResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ClienteResponseDTO cadastrarCliente(@RequestBody ClienteRequestDTO clienteRequestDTO) {
        return clienteService.cadastrarCliente(clienteRequestDTO);
    }

    @GetMapping
    public List<ClienteResponseDTO> encontrarTodosOsClientes() {
        return clienteService.encontrarTodosOsClientes();
    }

    @GetMapping("/{nome}")
    public ClienteResponseDTO encontrarClientePorNome(@PathVariable String nome) {
        return clienteService.encontrarPorNome(nome);
    }

    @PutMapping("/{nome}")
    public ClienteResponseDTO atualizarCliente(@PathVariable String nome, @RequestBody ClienteRequestDTO clienteRequestDTO) {
        return clienteService.atualizarCliente(nome, clienteRequestDTO);
    }

    @DeleteMapping("/{nome}")
    public void deletarClientePorNome(@PathVariable String nome) {
        clienteService.deletarPorNome(nome);
    }
}
