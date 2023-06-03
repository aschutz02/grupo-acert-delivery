package com.acert.delivery.controller.cadastro;


import com.acert.delivery.service.cliente.ClienteService;
import com.acert.delivery.service.cliente.dto.ClienteRequestDTO;
import com.acert.delivery.service.cliente.dto.ClienteResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cadastro")
public class CadastroController {

    private final ClienteService clienteService;

    @PostMapping()
    public ClienteResponseDTO cadastrarCliente(@RequestBody ClienteRequestDTO clienteRequestDTO) {
        return clienteService.cadastrarCliente(clienteRequestDTO);
    }
}
