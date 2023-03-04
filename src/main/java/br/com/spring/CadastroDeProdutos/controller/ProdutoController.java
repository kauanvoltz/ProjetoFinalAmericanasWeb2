package br.com.spring.CadastroDeProdutos.controller;

import br.com.spring.CadastroDeProdutos.dto.ProdutoRequestDTO;
import br.com.spring.CadastroDeProdutos.dto.ProdutoResponseDTO;
import br.com.spring.CadastroDeProdutos.model.Produto;
import br.com.spring.CadastroDeProdutos.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("produto")
@RequiredArgsConstructor
public class ProdutoController {
    private final ProdutoService produtoService;
    @PostMapping
    public String criarProduto(@RequestBody ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = new Produto();
        produto.setNome(produtoRequestDTO.getNome());
        produto.setDescricao(produtoRequestDTO.getDescricao());
        produto.setPreco(produtoRequestDTO.getPreco());

        produto = produtoService.criarProduto(produto);

        return produto.getId();
    }
    @GetMapping
    public List<ProdutoResponseDTO> exibirTodosProdutos() {
        return produtoService.obterTodosProdutos().stream().map(produto -> {
            ProdutoResponseDTO produtoResponse = new ProdutoResponseDTO();
            BeanUtils.copyProperties(produto, produtoResponse);
            return produtoResponse;
        }).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ProdutoResponseDTO exibirProdutoPorId(@PathVariable String id) {
        Produto produto = produtoService.obterProdutoPorId(id);
        ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO();
        produtoResponseDTO.setNome(produto.getNome());
        produtoResponseDTO.setDescricao(produto.getDescricao());
        produtoResponseDTO.setPreco(produto.getPreco());
        return produtoResponseDTO;
    }

    @PutMapping("{id}")
    public ProdutoResponseDTO atualizarProduto(@PathVariable String id, @RequestBody ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = produtoService.obterProdutoPorId(id);
        BeanUtils.copyProperties(produtoRequestDTO, produto);
        produto = produtoService.atualizarProduto(produto);
        ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO();
        BeanUtils.copyProperties(produto, produtoResponseDTO);
        return produtoResponseDTO;
    }

    @DeleteMapping("{id}")
    public void apagarProduto(@PathVariable String id) {
        produtoService.apagarProduto(id);
    }

}
