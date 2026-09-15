package br.com.ebac.productsApi.Controller;

import br.com.ebac.productsApi.Dto.CategoriaDto;
import br.com.ebac.productsApi.Dto.ProdutoRequestDto;
import br.com.ebac.productsApi.Dto.ProdutoResponseDto;
import br.com.ebac.productsApi.Model.Categoria;
import br.com.ebac.productsApi.Model.Produto;
import br.com.ebac.productsApi.Service.GerenciaService;
import br.com.ebac.productsApi.Utils.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class gerenciaController {

    public final GerenciaService service;

    public gerenciaController(GerenciaService service) {
        this.service = service;
    }

    @GetMapping("/produtos")
    public ResponseEntity<String> listarProdutos(){
        Page<Set<ProdutoResponseDto>> produtos = service.listarProdutos();
        return  ResponseEntity.status(HttpStatus.OK).body("produtos: \n" + produtos);
    }

    @PostMapping("/add-produto")
    public ResponseEntity<String> adicionarProdutos(@RequestBody ProdutoRequestDto requestDto){
        Produto produto = service.adicionarProduto(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Objeto adicionado com sucesso!");
    }

    @GetMapping("/procurar-produto-id")
    public ResponseEntity<ProdutoResponseDto> buscarProdutoPorId(@RequestParam Long id){
        ProdutoResponseDto produto =  service.buscarProdutoporId(id);
        return ResponseEntity.status(HttpStatus.OK).body(produto);
    }

    @GetMapping("/procurar-produto-nome")
    public ResponseEntity<Page<ProdutoResponseDto>> buscarProdutoPorNome(@RequestParam String nome){
        Page<ProdutoResponseDto> produto =  service.buscarProdutoPorNome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(produto);
    }

    @DeleteMapping("/deletar-produto")
    public ResponseEntity<String> deletarProdutoPorId(@RequestParam Long id){
        service.deletarProdutoPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body("Produto excluido: ");
    }

    @PostMapping("/add-categorias")
    public ResponseEntity<String> adicionarCategoria(@RequestBody CategoriaDto categoriaDto){
        Categoria categoria = service.adicionarCategoria(categoriaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Categorias: \n" + categoria);
    }
}