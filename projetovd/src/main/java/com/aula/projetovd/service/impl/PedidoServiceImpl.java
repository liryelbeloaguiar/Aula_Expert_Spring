package com.aula.projetovd.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.aop.IntroductionAdvisor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aula.projetovd.domain.entity.Cliente;
import com.aula.projetovd.domain.entity.ItemPedido;
import com.aula.projetovd.domain.entity.Pedido;
import com.aula.projetovd.domain.entity.Produto;
import com.aula.projetovd.domain.enums.StatusPedido;
import com.aula.projetovd.domain.repository.RepositoryCliente;
import com.aula.projetovd.domain.repository.RepositoryItemPedido;
import com.aula.projetovd.domain.repository.RepositoryPedido;
import com.aula.projetovd.domain.repository.RepositoryProduto;
import com.aula.projetovd.exception.PedidoNaoEncontradoException;
import com.aula.projetovd.exception.RegraNegocioException;
import com.aula.projetovd.rest.dto.ItemPedidoDTO;
import com.aula.projetovd.rest.dto.PedidoDTO;
import com.aula.projetovd.service.PedidoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {
    
    private final RepositoryPedido repositoryPedido;
    private final RepositoryCliente repositoryCliente;
    private final RepositoryProduto repositoryProduto;
    private final RepositoryItemPedido repositoryItemPedido;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = repositoryCliente.findById(idCliente)
        .orElseThrow(() -> new RegraNegocioException("Codigo de cliente Invalido"));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itemPedidos = converterItems(pedido, dto.getItems());
        repositoryPedido.save(pedido);
        repositoryItemPedido.saveAll(itemPedidos);
        pedido.setItems(itemPedidos);
        return pedido;
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
        if(items.isEmpty()){
            throw new RegraNegocioException("Não é possivel realizar pedido sem itens");
        }
        return items 
                .stream()
                .map( dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = 
                    repositoryProduto.findById(idProduto)
                        .orElseThrow(() -> new RegraNegocioException("Codigo de produto Invalido: " + idProduto));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
       return repositoryPedido.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        repositoryPedido
                        .findById(id)
                        .map( pedido -> {
                            pedido.setStatus(statusPedido);
                            return repositoryPedido.save(pedido);
                        }).orElseThrow(() -> new PedidoNaoEncontradoException());
    }
}
