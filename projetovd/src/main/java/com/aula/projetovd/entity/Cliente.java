package com.aula.projetovd.entity;


import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;

@Data //do Lombok para não precisar do get e do set
@Entity
@Table(name ="Cliente") //definições de tabela - quando forem diferentes os nomes
public class Cliente{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id") //definições de coluna - quando forem diferente os nomes
    private Integer id;
    
    @Column(name = "nome", length = 100)
    private String nome;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<Pedido> pedidos;

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Cliente() {
    }

    @Override
    public String toString() {
        return "Cliente [id=" + id + ", nome=" + nome + "]";
    }

    
}