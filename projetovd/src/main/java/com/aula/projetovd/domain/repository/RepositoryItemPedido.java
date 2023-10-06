package com.aula.projetovd.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aula.projetovd.domain.entity.ItemPedido;


public interface RepositoryItemPedido extends JpaRepository<ItemPedido, Integer>{
    
}
