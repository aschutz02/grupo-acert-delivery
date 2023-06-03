package com.acert.delivery.entity.pedido;

import com.acert.delivery.entity.cliente.Cliente;
import com.acert.delivery.entity.entrega.Entrega;
import com.acert.delivery.entity.produto.Produto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "pedidos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Pedido implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "entrega_id", referencedColumnName = "id")
	private Entrega entrega;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Produto> produtos;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id", referencedColumnName = "id")
	@JsonBackReference
	private Cliente cliente;

	@ElementCollection(fetch = FetchType.EAGER)
	@Column(name = "produtos_id")
	private List<Long> produtosId;

	@Column(name = "cliente_id")
	private Long clienteId;

	@Column(name = "entrega_id")
	private Long entregaId;

	@Column(nullable = false, name = "valor_total")
	private Double valorTotal;
}
