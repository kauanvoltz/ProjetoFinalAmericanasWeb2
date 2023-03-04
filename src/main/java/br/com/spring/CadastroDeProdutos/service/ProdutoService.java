package br.com.spring.CadastroDeProdutos.service;

import br.com.spring.CadastroDeProdutos.model.Produto;
import br.com.spring.CadastroDeProdutos.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public Produto criarProduto(Produto produto) {

        if (produto.getId() != null) {
            throw new IllegalArgumentException();
        }
        produto.setId(UUID.randomUUID().toString());
        produto.setUltimoUpdate(LocalDateTime.now());
        return produtoRepository.save(produto);
    }

    public Produto obterProdutoPorId(String id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public List<Produto> obterTodosProdutos(){
        List<Produto> listaDeProdutos = new ArrayList<>();
        produtoRepository.findAll().forEach(produto -> listaDeProdutos.add(produto));
        return listaDeProdutos;
    }

    public Produto atualizarProduto(Produto produto){
        produto.setUltimoUpdate(LocalDateTime.now());
        return produtoRepository.save(produto);
    }
    public Produto apagarProduto(String id) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        produtoRepository.delete(produto);
        return produto;
    }
}





