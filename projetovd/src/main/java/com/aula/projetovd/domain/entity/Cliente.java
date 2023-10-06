package com.aula.projetovd.domain.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

//Verificar o uso do lombok depois
@Entity
@Table(name ="Cliente") //definições de tabela - quando forem diferentes os nomes
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id") //definições de coluna - quando forem diferente os nomes
    private Integer id;
    
    @Column(name = "nome", length = 100)
    private String nome;

    @Column(name = "cpf", length = 11)
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<Pedido> pedidos;

    public Cliente(String nome) {
        this.nome = nome;
    }
    
}