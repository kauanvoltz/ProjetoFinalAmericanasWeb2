package br.com.spring.CadastroDeProdutos.controller;

import br.com.spring.CadastroDeProdutos.dto.ProdutoRequestDTO;
import br.com.spring.CadastroDeProdutos.model.Produto;
import br.com.spring.CadastroDeProdutos.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class ProdutoViewController {
    private final ProdutoService produtoService;
    private final ProdutoController produtoController;

    @RequestMapping(value = {"", "/", "/index"})
    public String showProdutoList (Model model){
        model.addAttribute("produtos", produtoService.obterTodosProdutos());
        return "index";
    }

    @GetMapping("/novo-produto")
    public String showNovoProduto(Produto produto) {return "produto";}

    @GetMapping("/edit/{id}")
    public String showEditProduto(@PathVariable("id") String id, Model model){
        Produto produto = produtoService.obterProdutoPorId(id);
        model.addAttribute("produto", produto);
        return "update-produto";
    }

    @PostMapping("/excluir/{id}")
    public String deleteProduto(@PathVariable("id") String id){
        produtoController.apagarProduto(id);
        return "redirect:/";
    }

    @PostMapping("/addproduto")
    public String addProduto(Produto produto, BindingResult result, Model model){
        if(result.hasErrors()){
            return "produto";
        }
        produtoService.criarProduto(produto);
        return "redirect:/";
    }

    @PostMapping("/update/{id}")
    public String updateProduto(@PathVariable("id") String id, Produto produto,BindingResult result, Model model){
        if(result.hasErrors()){
            produto.setId(id);
            return "update-produto";
        }
        ProdutoRequestDTO produtoRequestDTO = new ProdutoRequestDTO();
        produtoRequestDTO.setNome(produto.getNome());
        produtoRequestDTO.setDescricao(produto.getDescricao());
        produtoRequestDTO.setPreco(produto.getPreco());
       // produtoRequestDTO.setUltimoUpdate(produto.getUltimoUpdate());

        produtoController.atualizarProduto(produto.getId(), produtoRequestDTO);
        return "redirect:/index";

    }


}
