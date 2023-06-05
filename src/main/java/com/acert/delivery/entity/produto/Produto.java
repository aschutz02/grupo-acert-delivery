package com.acert.delivery.entity.produto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "produtos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "id")
public class Produto implements Serializable {

	@Id
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private Double preco;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "pedidos", referencedColumnName = "id")
//	@JsonBackReference
//	private Pedido pedido;
}
