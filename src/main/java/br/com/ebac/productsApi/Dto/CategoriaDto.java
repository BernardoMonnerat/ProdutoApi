package br.com.ebac.productsApi.Dto;

import java.util.Set;

public class CategoriaDto {

    private Long id;
    private String nome;
    private String descricao;
    private Set<ProdutoDto> produtosDto;

    public CategoriaDto(Long id, String nome, String descricao, Set<ProdutoDto> produtosDto) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.produtosDto = produtosDto;
    }

    public CategoriaDto() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<ProdutoDto> getProdutosDto() {
        return produtosDto;
    }

    public void setProdutosDto(Set<ProdutoDto> produtosDto) {
        this.produtosDto = produtosDto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
