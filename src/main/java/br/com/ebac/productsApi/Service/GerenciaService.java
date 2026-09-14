package br.com.ebac.productsApi.Service;

import br.com.ebac.productsApi.Dto.CategoriaDto;
import br.com.ebac.productsApi.Dto.ProdutoRequestDto;
import br.com.ebac.productsApi.Dto.ProdutoResponseDto;
import br.com.ebac.productsApi.Model.Categoria;
import br.com.ebac.productsApi.Model.Produto;
import br.com.ebac.productsApi.Repository.CategoriaRepository;
import br.com.ebac.productsApi.Repository.ProdutoRepository;
import br.com.ebac.productsApi.Utils.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;


@Service
public class GerenciaService {

    private final CategoriaRepository categoriaRepository;
    private final ProdutoRepository produtoRepository;


    public GerenciaService(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.produtoRepository = produtoRepository;
    }


    public Produto adicionarProduto(ProdutoRequestDto requestDto){
        Produto produto = Mapper.requestToProduto(requestDto);
        return produtoRepository.save(produto);
    }

    public Set<ProdutoResponseDto> listarProdutos(){

        Sort ordenacao = Sort.by("nome").ascending();

        PageRequest pedido = PageRequest.of(0, 2, ordenacao);

        return produtoRepository.findAll(pedido).stream()
                .map(p -> {

                    CategoriaDto categoriaDto = Mapper.categoriaToCategoriaDto(p.getCategoria());

                    return new ProdutoResponseDto(
                            p.getId(),
                            p.getNome(),
                            p.getPreco(),
                            categoriaDto,
                            p.getDescricao()
                    );
                })
                .collect(Collectors.toSet());
    }

    public Page<ProdutoResponseDto> buscarProdutoPorNome(String nome){

        Sort ordenacao = Sort.by("nome").ascending();

        PageRequest pedido = PageRequest.of(0, 2, ordenacao);
        Page<Produto> prod = produtoRepository.buscarPorNome(nome, pedido);
        Page<ProdutoResponseDto> pageDto = prod.map(produto -> Mapper.produtoToResponse(produto));

       return pageDto;
    }

    public ProdutoResponseDto buscarProdutoporId(Long id){
        Produto produto = produtoRepository.findById(id).get();

        return Mapper.produtoToResponse(produto);

    }

    public void deletarProdutoPorId(Long id){
        produtoRepository.deleteById(id);

    }

    public Categoria adicionarCategoria(CategoriaDto categoriaDto){

       Categoria categoria = Mapper.requestToCategoria(categoriaDto);

       return categoriaRepository.save(categoria);
    }

}
