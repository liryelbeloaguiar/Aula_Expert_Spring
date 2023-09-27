package com.aula.projetovd.entity;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Produto {
    private Integer id;
    private String Descricao;
    private BigDecimal preco;
}
