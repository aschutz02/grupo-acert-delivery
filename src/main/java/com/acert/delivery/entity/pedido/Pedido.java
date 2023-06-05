package com.acert.delivery.entity.pedido;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pedidos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Pedido implements Serializable {

	@Id
	private Long id;

//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "entrega_id", referencedColumnName = "id")
//	private Entrega entrega;

//	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Produto> produtos;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "cliente_id", referencedColumnName = "id")
//	@JsonBackReference
//	private Cliente cliente;

	@Column(name = "produto_id")
	private Long produtoId;

//	@JoinColumn(name = "cliente_id", referencedColumnName = "id")
	@Column(name = "cliente_id")
	private Long clienteId;

	@Column(name = "entrega_id")
	private Long entregaId;

	@Column(nullable = false, name = "valor_total")
	private Double valorTotal;
}
