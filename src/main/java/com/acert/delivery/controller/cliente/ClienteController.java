package com.acert.delivery.controller.cliente;

import com.acert.delivery.service.cliente.ClienteService;
import com.acert.delivery.service.cliente.dto.ClienteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ClienteDTO cadastrarCliente(@RequestBody ClienteDTO clienteDTO) {
        return clienteService.cadastrarCliente(clienteDTO);
    }

    @GetMapping
    public List<ClienteDTO> encontrarTodosOsClientes() {
        return clienteService.encontrarTodosOsClientes();
    }

    @GetMapping("/{nome}")
    public ClienteDTO encontrarClientePorNome(@PathVariable String nome) {
        return clienteService.encontrarPorNome(nome);
    }

    @PutMapping("/{nome}")
    public ClienteDTO atualizarCliente(@PathVariable String nome, @RequestBody ClienteDTO clienteDTO) {
        return clienteService.atualizarCliente(nome, clienteDTO);
    }

    @DeleteMapping("/{nome}")
    public void deletarClientePorNome(@PathVariable String nome) {
        clienteService.deletarPorNome(nome);
    }
}
