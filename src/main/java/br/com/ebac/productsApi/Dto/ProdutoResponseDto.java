package br.com.ebac.productsApi.Dto;


public class ProdutoResponseDto {

    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private CategoriaDto categoriaDto;

    public ProdutoResponseDto(Long id, String nome, Double preco, CategoriaDto categoriaDto, String descricao) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.categoriaDto = categoriaDto;
        this.descricao = descricao;
    }

    public ProdutoResponseDto() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategoriaDto(CategoriaDto categoriaDto) {
        this.categoriaDto = categoriaDto;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CategoriaDto getCategoriaDto() {
        return categoriaDto;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getPreco(Double preco) {
        return this.preco;
    }
}
