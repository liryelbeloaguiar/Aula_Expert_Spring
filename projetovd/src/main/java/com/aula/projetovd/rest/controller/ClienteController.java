package com.aula.projetovd.rest.controller;

import java.util.List;

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

import static org.springframework.http.HttpStatus.*;

import com.aula.projetovd.domain.entity.Cliente;
import com.aula.projetovd.domain.repository.RepositoryCliente;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class ClienteController {

    private RepositoryCliente repositoryCliente;

    public ClienteController(RepositoryCliente repositoryCliente) {
        this.repositoryCliente = repositoryCliente;
    }

    @GetMapping(value = "/clientes/{id}")
    public Cliente getClienteById(@PathVariable Integer id) {
        return repositoryCliente
                    .findById(id)
                    .orElseThrow(() -> 
                        new ResponseStatusException(NOT_FOUND,
                                                    "Cliente não encontrado"));
    }

    @PostMapping("/clientes")
    @ResponseStatus(CREATED)
    public Cliente save( @RequestBody @Valid Cliente cliente){
        return repositoryCliente.save(cliente);
    }

    @GetMapping(value = "/clientesTodos")
    public List<Cliente> getClienteTodos() {
        List<Cliente> cliente = repositoryCliente.findAll();
        if(!cliente.isEmpty()){
             return cliente;
         }
        throw new ResponseStatusException(NOT_FOUND,
                                                    "Cliente não encontrado");
    }

    @DeleteMapping("/deletar/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Integer id){
        repositoryCliente.findById(id)
                         .map( cliente -> { 
                            repositoryCliente.delete(cliente);
                            return cliente;
                        })
                         .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                                                                       "Cliente não encontrado"));
    }


    @PutMapping("/atualizar/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update(@RequestBody @Valid Cliente cliente, 
                                 @PathVariable Integer id) {
         repositoryCliente
                            .findById(id)
                            .map(clienteExistente -> {
                                cliente.setId(clienteExistente.getId());
                                repositoryCliente.save(cliente);
                                return clienteExistente;
                            }).orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                                                                       "Cliente não encontrado"));
    }

    @GetMapping("/encontrar")
    public List<Cliente> find(Cliente filtro){
        ExampleMatcher matcher = ExampleMatcher
                                .matching()
                                .withIgnoreCase()
                                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
        return repositoryCliente.findAll(example);
    }

}
