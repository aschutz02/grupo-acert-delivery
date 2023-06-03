package com.acert.delivery.controller.entrega;

import com.acert.delivery.service.entrega.EntregaService;
import com.acert.delivery.service.entrega.dto.EntregaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/entrega")
public class EntregaController {

    private final EntregaService entregaService;

    @PostMapping
    public EntregaDTO cadastrarEntrega(@RequestBody EntregaDTO entregaDTO) {
        return entregaService.cadastrarEntrega(entregaDTO);
    }

    @GetMapping
    public List<EntregaDTO> encontrarTodasAsEntregas() {
        return entregaService.encontrarTodasAsEntregas();
    }

    @GetMapping("/{id}")
    public EntregaDTO encontrarEntregaPorId(@PathVariable Long id) {
        return entregaService.encontrarPorId(id);
    }

    @PutMapping("/{id}")
    public EntregaDTO atualizarEntrega(@PathVariable Long id, @RequestBody EntregaDTO entregaDTO) {
        return entregaService.atualizarEntrega(id, entregaDTO);
    }

    @DeleteMapping("/{id}")
    public void deletarEntregaPorId(@PathVariable Long id) {
        entregaService.deletarPorId(id);
    }
}
