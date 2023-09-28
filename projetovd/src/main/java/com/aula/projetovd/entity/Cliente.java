package com.aula.projetovd.entity;


import io.micrometer.common.lang.NonNull;
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
    
    @NonNull
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