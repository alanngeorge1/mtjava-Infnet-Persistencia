package com.infnet.projeto.persistencia.service;

import com.infnet.projeto.persistencia.domain.entity.Pedido;
import com.infnet.projeto.persistencia.domain.enums.StatusPedido;
import com.infnet.projeto.persistencia.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto (Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
