package com.acert.delivery.controller.cadastro;


import com.acert.delivery.service.cliente.ClienteService;
import com.acert.delivery.service.cliente.dto.ClienteRequestDTO;
import com.acert.delivery.service.cliente.dto.ClienteResponseDTO;
import com.acert.delivery.service.entrega.dto.EntregaDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cadastro")
public class CadastroController {

    private final ClienteService clienteService;

    @ApiOperation(value = "Cadastrar um cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente cadastrado", response = ClienteResponseDTO.class),
            @ApiResponse(code = 400, message = "Erro na requisição"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDTO cadastrarCliente(@RequestBody @Valid ClienteRequestDTO clienteRequestDTO) {
        return clienteService.cadastrarCliente(clienteRequestDTO);
    }
}
