package com.aula.projetovd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aula.projetovd.entity.Pedido;
import java.util.List;
import com.aula.projetovd.entity.Cliente;


public interface RepositoryPedido extends JpaRepository<Pedido, Integer>{
    List<Pedido> findByCliente(Cliente cliente);
}
