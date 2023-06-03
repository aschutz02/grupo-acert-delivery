package com.acert.delivery.controller.pedido;

import com.acert.delivery.service.pedido.PedidoService;
import com.acert.delivery.service.pedido.dto.PedidoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public PedidoDTO cadastrarPedido(@RequestBody PedidoDTO pedidoDTO) {
        return pedidoService.cadastrarPedido(pedidoDTO);
    }

    @GetMapping
    public List<PedidoDTO> encontrarTodosOsPedidos() {
        return pedidoService.encontrarTodosOsPedidos();
    }

    @GetMapping("/{id}")
    public PedidoDTO encontrarPedidoPorId(@PathVariable Long id) {
        return pedidoService.encontrarPorId(id);
    }

    @PutMapping("/{id}")
    public PedidoDTO atualizarPedido(@PathVariable Long id, @RequestBody PedidoDTO pedidoDTO) {
        return pedidoService.atualizarPedido(id, pedidoDTO);
    }

    @DeleteMapping("/{id}")
    public void deletarPedidoPorId(@PathVariable Long id) {
        pedidoService.deletarPorId(id);
    }
}
