package com.acert.delivery.controller.produto;

import com.acert.delivery.service.produto.ProdutoService;
import com.acert.delivery.service.produto.dto.ProdutoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ProdutoDTO cadastrarProduto(@RequestBody ProdutoDTO produtoDTO) {
        return produtoService.cadastrarProduto(produtoDTO);
    }

    @GetMapping
    public List<ProdutoDTO> encontrarTodosOsProdutos() {
        return produtoService.encontrarTodosOsProdutos();
    }

    @GetMapping("/{nome}")
    public ProdutoDTO encontrarProdutoPorNome(@PathVariable String nome) {
        return produtoService.encontrarPorNome(nome);
    }

    @PutMapping("/{nome}")
    public ProdutoDTO atualizarProduto(@PathVariable String nome, @RequestBody ProdutoDTO produtoDTO) {
        return produtoService.atualizarProduto(nome, produtoDTO);
    }

    @DeleteMapping("/{nome}")
    public void deletarProdutoPorNome(@PathVariable String nome) {
        produtoService.deletarPorNome(nome);
    }
}
