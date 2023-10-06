package com.aula.projetovd.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.aula.projetovd.domain.enums.StatusPedido;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "PEDIDO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID")
    private Cliente cliente;

    @Column(name = "DATA_PEDIDO")
    private LocalDate dataPedido;

    @Column(name = "TOTAL", length = 20)
    private BigDecimal total;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> items;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPedido status;

}
