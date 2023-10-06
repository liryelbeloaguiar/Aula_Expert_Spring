package com.aula.projetovd.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aula.projetovd.domain.entity.Pedido;
import java.util.List;
import java.util.Optional;

import com.aula.projetovd.domain.entity.Cliente;


public interface RepositoryPedido extends JpaRepository<Pedido, Integer>{
    List<Pedido> findByCliente(Cliente cliente);
    
    @Query(" select p from Pedido p left join fetch p.items where p.id = :id ") 
    Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);
}
