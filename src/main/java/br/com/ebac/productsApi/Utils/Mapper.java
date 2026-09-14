package br.com.ebac.productsApi.Utils;

import br.com.ebac.productsApi.Dto.CategoriaDto;
import br.com.ebac.productsApi.Dto.ProdutoDto;
import br.com.ebac.productsApi.Dto.ProdutoRequestDto;
import br.com.ebac.productsApi.Dto.ProdutoResponseDto;
import br.com.ebac.productsApi.Model.Categoria;
import br.com.ebac.productsApi.Model.Produto;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class Mapper {

    public static Produto requestToProduto(ProdutoRequestDto requestDto){
        Produto produto =  new Produto();

        produto.setId(requestDto.getId());
        produto.setNome(requestDto.getNome());
        produto.setDescricao(requestDto.getDescricao());
        produto.setPreco(requestDto.getPreco());

        Categoria categoria = new Categoria();
        categoria.setId(requestDto.getCategoriaDto().getId());
        categoria.setNome(requestDto.getCategoriaDto().getNome());
        categoria.setDescricao(requestDto.getCategoriaDto().getDescricao());

        produto.setCategoria(categoria);
        return produto;
    }

    public static ProdutoResponseDto produtoToResponse(Produto produto){
        ProdutoResponseDto responseDto = new ProdutoResponseDto();

        responseDto.setId(produto.getId());
        responseDto.setNome(produto.getNome());
        responseDto.setDescricao(produto.getDescricao());
        responseDto.setPreco(produto.getPreco());

        CategoriaDto categoriaDto =  new CategoriaDto();
        categoriaDto.setId(produto.getCategoria().getId());
        categoriaDto.setNome(produto.getCategoria().getNome());
        categoriaDto.setDescricao(produto.getCategoria().getDescricao());

        responseDto.setCategoriaDto(categoriaDto);

        return responseDto;
    }

    public static Categoria requestToCategoria(CategoriaDto categoriaDto) {
        if (categoriaDto == null) {
            return null;
        }

        Categoria categoria = new Categoria();
        categoria.setId(categoriaDto.getId());
        categoria.setNome(categoriaDto.getNome());
        categoria.setDescricao(categoriaDto.getDescricao());

        return categoria;
    }

    public static CategoriaDto categoriaToCategoriaDto(Categoria categoria) {
        if (categoria == null) {
            return null;
        }

        CategoriaDto categoriaDto = new CategoriaDto();
        categoriaDto.setId(categoria.getId());
        categoriaDto.setNome(categoria.getNome());
        categoriaDto.setDescricao(categoria.getDescricao());

        return categoriaDto;
    }
}
