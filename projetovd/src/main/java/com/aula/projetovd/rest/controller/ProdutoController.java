package com.aula.projetovd.rest.controller;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.aula.projetovd.domain.entity.Produto;
import com.aula.projetovd.domain.repository.RepositoryProduto;
import static org.springframework.http.HttpStatus.*;

import java.util.List;

@RestController
@RequestMapping("/apiProdutos")
public class ProdutoController {
    

    private RepositoryProduto repositoryProduto;

    public ProdutoController(RepositoryProduto repositoryProduto) {
        this.repositoryProduto = repositoryProduto;
    }

    @PostMapping("/produtos")
    @ResponseStatus(CREATED)
    public Produto save(@RequestBody Produto produto){
        return repositoryProduto.save(produto);
    }

    @GetMapping(value = "/produtos/{id}")
    public Produto getProdutoById(@PathVariable Integer id){
        return repositoryProduto
                    .findById(id)
                    .orElseThrow(() -> 
                        new ResponseStatusException(NOT_FOUND, "Produto não encontrado"));
    }

    @GetMapping(value = "/produtosTodos")
    public List<Produto> getClienteTodos(){
        List<Produto> produto = repositoryProduto.findAll();
        if(!produto.isEmpty()){
            return produto;
        }
        throw new ResponseStatusException(NOT_FOUND, "Produto não encontrado");
    }

    @DeleteMapping("/deletar/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Integer id){
        repositoryProduto.findById(id)
                         .map( produto -> {
                            repositoryProduto.delete(produto);
                            return produto;
                         })
                         .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Produto não encontrado"));
    }

    @PutMapping("/atualizar/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update(@RequestBody Produto produto,
                        @PathVariable Integer id){
        repositoryProduto  
                    .findById(id)
                    .map(produtoExistente -> {
                        produto.setId(produtoExistente.getId());
                        repositoryProduto.save(produto);
                        return produtoExistente;
                    }).orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                                                                    "Poduto não encontrado"));                    
    }

    @GetMapping("/encontrar")
    public List<Produto> find(Produto filtro){
        ExampleMatcher matcher = ExampleMatcher
                                .matching()
                                .withIgnoreCase()
                                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
        return repositoryProduto.findAll(example);
    }
}
