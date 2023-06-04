package com.acert.delivery.controller.login;


import com.acert.delivery.service.cliente.ClienteService;
import com.acert.delivery.service.cliente.dto.ClienteResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Login dos usários")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Login com sucesso", response = ClienteResponseDTO.class),
            @ApiResponse(code = 400, message = "Credenciais inválidas"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @PostMapping("/auth")
    public ClienteResponseDTO login(Authentication authentication) {
        ClienteResponseDTO clientes = clienteService.encontrarPeloEmail(authentication.getName());
        if (nonNull(clientes)) {
            return clientes;
        } else {
            return null;
        }
    }
}
