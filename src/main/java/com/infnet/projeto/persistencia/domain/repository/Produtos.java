package com.infnet.projeto.persistencia.domain.repository;

import com.infnet.projeto.persistencia.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {
}



