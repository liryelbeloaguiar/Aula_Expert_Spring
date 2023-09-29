package com.aula.projetovd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aula.projetovd.entity.ItemPedido;


public interface RepositoryItemPedido extends JpaRepository<ItemPedido, Integer>{
    
}
