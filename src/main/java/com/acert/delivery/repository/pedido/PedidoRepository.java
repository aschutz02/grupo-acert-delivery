package com.acert.delivery.repository.pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acert.delivery.entity.pedido.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
