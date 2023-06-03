package com.acert.delivery.controller;

import com.acert.delivery.service.produto.ProdutoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResponseErrorHandler {

    @ExceptionHandler(ProdutoNotFoundException.class)
    public ResponseEntity<String> gerennciarProdutoNotFoundException(ProdutoNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
