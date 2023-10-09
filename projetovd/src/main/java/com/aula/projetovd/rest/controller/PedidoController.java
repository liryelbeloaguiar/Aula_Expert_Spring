package com.aula.projetovd.rest.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.aula.projetovd.domain.entity.ItemPedido;
import com.aula.projetovd.domain.entity.Pedido;
import com.aula.projetovd.domain.enums.StatusPedido;
import com.aula.projetovd.rest.dto.AtualizacaoStatusPedidoDTO;
import com.aula.projetovd.rest.dto.InformacaoItemPedidoDTO;
import com.aula.projetovd.rest.dto.InformacoesPedidoDTO;
import com.aula.projetovd.rest.dto.PedidoDTO;
import com.aula.projetovd.service.PedidoService;

import jakarta.validation.Valid;

import static org.springframework.http.HttpStatus.*;

import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/apiPedidos")
public class PedidoController {
    
    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }
    
    @PostMapping("/pedidos")
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody @Valid PedidoDTO dto){
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id){
        return service
                .obterPedidoCompleto(id)
                .map(p -> converter(p))
                .orElseThrow(() -> 
                            new ResponseStatusException(NOT_FOUND, "Pedido não encontrado."));
    }

    @PatchMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateStatus(@RequestBody AtualizacaoStatusPedidoDTO dto, 
                             @PathVariable Integer id){
        String novoStatus = dto.getNovoStatus();
        service.atualizaStatus(id, StatusPedido.valueOf(novoStatus));                        
    }

    private InformacoesPedidoDTO converter(Pedido pedido){
      return InformacoesPedidoDTO.builder()
                            .codigo(pedido.getId())
                            .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                            .cpf(pedido.getCliente().getCpf())
                            .nomeCliente(pedido.getCliente().getNome())
                            .total(pedido.getTotal())
                            .status(pedido.getStatus().name())
                            .items(converter(pedido.getItems()))
                            .build();
      }    
      
      private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> items){
       if(CollectionUtils.isEmpty(items)){
            return Collections.emptyList();
       }
       return items.stream().map(
              item -> InformacaoItemPedidoDTO
                      .builder().descricaoProduto(item.getProduto().getDescricao())
                      .precoUnitario(item.getProduto().getPreco())
                      .quantidade(item.getQuantidade())
                      .build()
       ).collect(Collectors.toList());
      }
}
