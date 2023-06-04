package com.acert.delivery.controller.entrega;

import com.acert.delivery.service.entrega.EntregaService;
import com.acert.delivery.service.entrega.dto.EntregaDTO;
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
@RequestMapping("/entrega")
public class EntregaController {

    private final EntregaService entregaService;

    @ApiOperation(value = "Cadastrar uma entrega")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Entrega cadastrada", response = EntregaDTO.class),
            @ApiResponse(code = 400, message = "Erro na requisição"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaDTO cadastrarEntrega(@RequestBody @Valid EntregaDTO entregaDTO) {
        return entregaService.cadastrarEntrega(entregaDTO);
    }

    @ApiOperation(value = "Listar todas as entregas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Entregas encontradas", response = EntregaDTO.class),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @GetMapping
    public List<EntregaDTO> encontrarTodasAsEntregas() {
        return entregaService.encontrarTodasAsEntregas();
    }

    @ApiOperation(value = "Encontrar uma entrega pelo id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Entrega encontrada", response = EntregaDTO.class),
            @ApiResponse(code = 404, message = "Entrega não encontrada"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @GetMapping("/{id}")
    public EntregaDTO encontrarEntregaPorId(@PathVariable Long id) {
        return entregaService.encontrarPeloId(id);
    }

    @ApiOperation(value = "Atualizar uma entrega")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Entrega atualizada", response = EntregaDTO.class),
            @ApiResponse(code = 400, message = "Erro na requisição"),
            @ApiResponse(code = 404, message = "Entrega não encontrada"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @PutMapping("/{id}")
    public EntregaDTO atualizarEntrega(@PathVariable Long id, @RequestBody @Valid EntregaDTO entregaDTO) {
        return entregaService.atualizarEntrega(id, entregaDTO);
    }

    @ApiOperation(value = "Deletar uma entrega pelo id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Entrega deletada"),
            @ApiResponse(code = 404, message = "Entrega não encontrada"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarEntregaPeloId(@PathVariable Long id) {
        entregaService.deletarPeloId(id);
    }
}
