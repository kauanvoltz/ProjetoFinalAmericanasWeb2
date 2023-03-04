package br.com.spring.CadastroDeProdutos.repository;

import br.com.spring.CadastroDeProdutos.model.Produto;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepository extends CrudRepository<Produto, String> {
}
