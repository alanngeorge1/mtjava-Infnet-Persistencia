package com.infnet.projeto.persistencia.domain.repository;

import com.infnet.projeto.persistencia.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsPedido extends JpaRepository<ItemPedido, Integer> {
}
