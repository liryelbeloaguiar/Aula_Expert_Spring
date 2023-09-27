package com.aula.projetovd.entity;

import lombok.Data;

@Data
public class Cliente{

    private Integer id;
    private String nome;
    
    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Cliente() {
    }


}