package com.aula.projetovd.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "PRODUTO")
public class Produto {
    
    private Integer id;
    private String Descricao;
    private BigDecimal preco;
}
