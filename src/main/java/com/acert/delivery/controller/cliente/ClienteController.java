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

    @GetMapping
    public List<ClienteResponseDTO> encontrarTodosOsClientes() {
        return clienteService.encontrarTodosOsClientes();
    }

    @GetMapping("/{email}")
    public ClienteResponseDTO encontrarClientePorEmail(@PathVariable String email) {
        return clienteService.encontrarPorEmail(email);
    }

    @PutMapping("/{email}")
    public ClienteResponseDTO atualizarCliente(@PathVariable String email, @RequestBody ClienteRequestDTO clienteRequestDTO) {
        return clienteService.atualizarCliente(email, clienteRequestDTO);
    }

    @DeleteMapping("/{email}")
    public void deletarClientePorEmail(@PathVariable String email) {
        clienteService.deletarPorEmail(email);
    }
}
