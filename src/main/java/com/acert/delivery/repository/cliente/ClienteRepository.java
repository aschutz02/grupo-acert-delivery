package com.acert.delivery.repository.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acert.delivery.entity.cliente.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
