package com.acert.delivery.repository.entrega;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acert.delivery.entity.entrega.Entrega;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {
}
