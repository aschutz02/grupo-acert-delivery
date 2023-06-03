package com.acert.delivery.repository.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acert.delivery.entity.produto.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
