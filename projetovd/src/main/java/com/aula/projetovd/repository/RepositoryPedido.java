package com.aula.projetovd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aula.projetovd.entity.Pedido;

public interface RepositoryPedido extends JpaRepository<Pedido, Integer>{
    
}
