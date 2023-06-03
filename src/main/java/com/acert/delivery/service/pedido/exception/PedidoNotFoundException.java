package com.acert.delivery.service.pedido.exception;

public class PedidoNotFoundException extends RuntimeException {

    public PedidoNotFoundException(String message) {
        super(message);
    }

}
