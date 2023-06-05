package com.acert.delivery.entity.cliente;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "clientes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Cliente implements Serializable {

	@Id
	private Long id;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String senha;

//	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Pedido> pedidos;
}
