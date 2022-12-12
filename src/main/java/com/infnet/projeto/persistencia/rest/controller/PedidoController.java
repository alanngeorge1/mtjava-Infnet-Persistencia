package com.infnet.projeto.persistencia.rest.controller;

import com.infnet.projeto.persistencia.domain.entity.ItemPedido;
import com.infnet.projeto.persistencia.domain.entity.Pedido;
import com.infnet.projeto.persistencia.domain.enums.StatusPedido;
import com.infnet.projeto.persistencia.rest.dto.AtualizacaoStatusPedidoDTO;
import com.infnet.projeto.persistencia.rest.dto.InfomacaoItempedidoDTO;
import com.infnet.projeto.persistencia.rest.dto.InformacoesPeditoDTO;
import com.infnet.projeto.persistencia.rest.dto.PedidoDTO;
import com.infnet.projeto.persistencia.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save (@RequestBody @Valid PedidoDTO dto){
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }

    @GetMapping ("{id}")
    public InformacoesPeditoDTO getById(@PathVariable Integer id){
        return service.obterPedidoCompleto(id)
                .map(p -> converter(p))
                .orElseThrow(()->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

    private InformacoesPeditoDTO converter (Pedido pedido){
        return InformacoesPeditoDTO.builder()
                .codigo(pedido.getId())
                .datapedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .items(converter(pedido.getItens()))
                .status(pedido.getStatus().name())
                .build();

    }

    @PatchMapping ("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@RequestBody AtualizacaoStatusPedidoDTO dto, @PathVariable Integer id){
        String novoStatus = dto.getNovoStatus();
        service.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }

    private List<InfomacaoItempedidoDTO> converter (List<ItemPedido> itens){
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }
        return itens.stream().map(item -> InfomacaoItempedidoDTO.builder()
                .descricaoProduto(item.getProduto().getDescricao())
                .precoUnitario(item.getProduto().getPreco())
                .build()
        ).collect(Collectors.toList());

    }
}