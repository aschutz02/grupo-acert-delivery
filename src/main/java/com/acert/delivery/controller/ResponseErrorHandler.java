package com.acert.delivery.controller;

import com.acert.delivery.service.cliente.exception.ClienteNotFoundException;
import com.acert.delivery.service.entrega.exception.EntregaNotFoundException;
import com.acert.delivery.service.pedido.exception.PedidoNotFoundException;
import com.acert.delivery.service.produto.exception.ProdutoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResponseErrorHandler {

    @ExceptionHandler(ProdutoNotFoundException.class)
    public ResponseEntity<String> gerenciarProdutoNotFoundException(ProdutoNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntregaNotFoundException.class)
    public ResponseEntity<String> gerenciarEntregaNotFoundException(EntregaNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<String> gerenciarClienteNotFoundException(ClienteNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PedidoNotFoundException.class)
    public ResponseEntity<String> gerenciarPedidoNotFoundException(PedidoNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
