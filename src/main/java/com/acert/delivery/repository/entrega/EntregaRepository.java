package com.acert.delivery.repository.entrega;

import com.acert.delivery.entity.entrega.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {

    Optional<Entrega> findByNome(String nome);
    void deleteEntregaByNome(String nome);
}
