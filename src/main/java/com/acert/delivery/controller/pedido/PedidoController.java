package com.acert.delivery.controller.pedido;

import com.acert.delivery.service.pedido.PedidoService;
import com.acert.delivery.service.pedido.dto.PedidoRequestDTO;
import com.acert.delivery.service.pedido.dto.PedidoResponseDTO;
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
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    @ApiOperation(value = "Cadastrar um pedido")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pedido cadastrado", response = PedidoResponseDTO.class),
            @ApiResponse(code = 400, message = "Erro na requisição"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResponseDTO cadastrarPedido(@RequestBody @Valid PedidoRequestDTO pedidoRequestDTO) {
        return pedidoService.cadastrarPedido(pedidoRequestDTO);
    }

    @ApiOperation(value = "Listar pedidos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pedidos encontrados", response = PedidoResponseDTO.class),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @GetMapping
    public List<PedidoResponseDTO> encontrarTodosOsPedidos() {
        return pedidoService.encontrarTodosOsPedidos();
    }

    @ApiOperation(value = "Encontrar pedidos pelo Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pedido encontrado", response = PedidoResponseDTO.class),
            @ApiResponse(code = 404, message = "Pedido não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @GetMapping("/{id}")
    public PedidoResponseDTO encontrarPedidoPeloId(@PathVariable Long id) {
        return pedidoService.encontrarPeloId(id);
    }

    @ApiOperation(value = "Atualizar um pedido")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pedido atualizado", response = PedidoResponseDTO.class),
            @ApiResponse(code = 400, message = "Erro na requisição"),
            @ApiResponse(code = 404, message = "Pedido não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @PutMapping("/{id}")
    public PedidoResponseDTO atualizarPedido(@PathVariable Long id, @RequestBody @Valid PedidoRequestDTO pedidoRequestDTO) {
        return pedidoService.atualizarPedido(id, pedidoRequestDTO);
    }

    @ApiOperation(value = "Deletar um pedido")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Pedido deletado"),
            @ApiResponse(code = 404, message = "Pedido não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletarPedidoPeloId(@PathVariable Long id) {
        pedidoService.deletarPeloId(id);
    }
}
