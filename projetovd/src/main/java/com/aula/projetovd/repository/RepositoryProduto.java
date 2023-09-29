package com.aula.projetovd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aula.projetovd.entity.Produto;

public interface RepositoryProduto extends JpaRepository<Produto, Integer> {
    
}
