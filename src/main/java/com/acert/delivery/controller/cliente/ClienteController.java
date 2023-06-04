package com.acert.delivery.controller.cliente;

import com.acert.delivery.service.cliente.ClienteService;
import com.acert.delivery.service.cliente.dto.ClienteRequestDTO;
import com.acert.delivery.service.cliente.dto.ClienteResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @ApiOperation(value = "Listar clientes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Clientes encontrados", response = ClienteResponseDTO.class),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @GetMapping
    public List<ClienteResponseDTO> encontrarTodosOsClientes() {
        return clienteService.encontrarTodosOsClientes();
    }

    @ApiOperation(value = "Encontrar cliente pelo email")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente encontrado", response = ClienteResponseDTO.class),
            @ApiResponse(code = 404, message = "Cliente não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @GetMapping("/{email}")
    public ClienteResponseDTO encontrarClientePeloEmail(@PathVariable String email) {
        return clienteService.encontrarPeloEmail(email);
    }

    @ApiOperation(value = "Atualizar um cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente atualizado", response = ClienteResponseDTO.class),
            @ApiResponse(code = 400, message = "Erro na requisição"),
            @ApiResponse(code = 404, message = "Cliente não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @PutMapping("/{email}")
    public ClienteResponseDTO atualizarCliente(@PathVariable String email, @RequestBody @Valid ClienteRequestDTO clienteRequestDTO) {
        return clienteService.atualizarCliente(email, clienteRequestDTO);
    }

    @ApiOperation(value = "Deletar um cliente pelo email")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Cliente deletado"),
            @ApiResponse(code = 404, message = "Cliente não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{email}")
    public void deletarClientePeloEmail(@PathVariable String email) {
        clienteService.deletarPeloEmail(email);
    }
}
