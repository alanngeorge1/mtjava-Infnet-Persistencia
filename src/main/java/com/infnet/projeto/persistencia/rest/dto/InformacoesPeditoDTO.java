package com.infnet.projeto.persistencia.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesPeditoDTO {
    private  Integer codigo;
    private String cpf;
    private String nomeCliente;
    private BigDecimal total;
    private String datapedido;
    private List<InfomacaoItempedidoDTO> items;
    private String status;
}
