package com.aula.projetovd.domain.entity;

import java.util.Set;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @Column(name = "cpf", length = 11)
    @NotEmpty(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "{campo.cpf.invalido}")
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<Pedido> pedidos;

    public Cliente(String nome) {
        this.nome = nome;
    }
    
}