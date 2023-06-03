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

    @GetMapping("/{nome}")
    public EntregaDTO encontrarEntregaPorNome(@PathVariable String nome) {
        return entregaService.encontrarPorNome(nome);
    }

    @PutMapping("/{nome}")
    public EntregaDTO atualizarEntrega(@PathVariable String nome, @RequestBody EntregaDTO entregaDTO) {
        return entregaService.atualizarEntrega(nome, entregaDTO);
    }

    @DeleteMapping("/{nome}")
    public void deletarEntregaPorNome(@PathVariable String nome) {
        entregaService.deletarPorNome(nome);
    }
}
