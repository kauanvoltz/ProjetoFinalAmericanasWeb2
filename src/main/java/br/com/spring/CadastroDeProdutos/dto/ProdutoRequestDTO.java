package br.com.spring.CadastroDeProdutos.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProdutoRequestDTO {

    private String nome;

    private String descricao;

    private Double preco;

    private LocalDateTime ultimoUpdate;
}
