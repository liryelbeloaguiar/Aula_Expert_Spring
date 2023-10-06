package com.aula.projetovd.exception;

public class PedidoNaoEncontradoException extends RuntimeException{

    public PedidoNaoEncontradoException(){
        super("Pedido não encontrado.");
    }

}
