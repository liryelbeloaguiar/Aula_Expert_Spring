package com.aula.projetovd.entity;

import lombok.Data;

@Data
public class itemPedido {
    
    private Integer id;
    private Pedido pedido;
    private Produto produto;
    private Integer quantidade;

}
