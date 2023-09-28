package com.aula.projetovd.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "ITEM_PEDIDO")
public class ItemPedido {
    
    private Integer id;
    private Pedido pedido;
    private Produto produto;
    private Integer quantidade;

}
