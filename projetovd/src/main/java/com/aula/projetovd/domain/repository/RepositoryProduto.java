package com.aula.projetovd.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aula.projetovd.domain.entity.Produto;

public interface RepositoryProduto extends JpaRepository<Produto, Integer> {
    
}
