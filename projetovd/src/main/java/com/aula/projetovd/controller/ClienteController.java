package com.aula.projetovd.controller;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aula.projetovd.entity.Cliente;
import com.aula.projetovd.repository.RepositoryCliente;

@Controller
@RequestMapping("/api")
public class ClienteController {

    private RepositoryCliente repositoryCliente;

    public ClienteController(RepositoryCliente repositoryCliente) {
        this.repositoryCliente = repositoryCliente;
    }

    @GetMapping(value = "/clientes/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
        Optional<Cliente> cliente = repositoryCliente.findById(id);

        if(cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    
}
