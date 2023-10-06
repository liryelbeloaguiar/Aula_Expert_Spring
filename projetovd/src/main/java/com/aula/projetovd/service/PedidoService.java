package com.aula.projetovd.service;

import java.util.Optional;

import com.aula.projetovd.domain.entity.Pedido;
import com.aula.projetovd.domain.enums.StatusPedido;
import com.aula.projetovd.rest.dto.PedidoDTO;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
