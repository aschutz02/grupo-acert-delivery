package com.acert.delivery.controller.login;


import com.acert.delivery.service.cliente.ClienteService;
import com.acert.delivery.service.cliente.dto.ClienteResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@RestController
@RequestMapping("/login")
public class LoginController {

    private final ClienteService clienteService;

    @PostMapping("/auth")
    public ClienteResponseDTO login(Authentication authentication) {
        ClienteResponseDTO clientes = clienteService.encontrarPorEmail(authentication.getName());
        if (nonNull(clientes)) {
            return clientes;
        } else {
            return null;
        }
    }
}
