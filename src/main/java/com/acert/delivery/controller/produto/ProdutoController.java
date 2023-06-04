package com.acert.delivery.controller.produto;

import com.acert.delivery.service.produto.ProdutoService;
import com.acert.delivery.service.produto.dto.ProdutoDTO;
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
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    @ApiOperation(value = "Cadastrar um produto")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Produto cadastrado", response = ProdutoDTO.class),
            @ApiResponse(code = 400, message = "Erro na requisição"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDTO cadastrarProduto(@RequestBody @Valid ProdutoDTO produtoDTO) {
        return produtoService.cadastrarProduto(produtoDTO);
    }

    @ApiOperation(value = "Listar produtos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produtos encontrados", response = ProdutoDTO.class),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @GetMapping
    public List<ProdutoDTO> encontrarTodosOsProdutos() {
        return produtoService.encontrarTodosOsProdutos();
    }

    @ApiOperation(value = "Encontrar produto pelo nome")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto encontrado", response = ProdutoDTO.class),
            @ApiResponse(code = 404, message = "Produto não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @GetMapping("/{nome}")
    public ProdutoDTO encontrarProdutoPeloNome(@PathVariable String nome) {
        return produtoService.encontrarPeloNome(nome);
    }

    @ApiOperation(value = "Atualizar produto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto atualizado", response = ProdutoDTO.class),
            @ApiResponse(code = 400, message = "Erro na requisição"),
            @ApiResponse(code = 404, message = "Produto não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @PutMapping("/{nome}")
    public ProdutoDTO atualizarProduto(@PathVariable String nome, @RequestBody @Valid ProdutoDTO produtoDTO) {
        return produtoService.atualizarProduto(nome, produtoDTO);
    }

    @ApiOperation(value = "Deletar produto")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Produto deletado"),
            @ApiResponse(code = 404, message = "Produto não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{nome}")
    public void deletarProdutoPeloNome(@PathVariable String nome) {
        produtoService.deletarPeloNome(nome);
    }
}
