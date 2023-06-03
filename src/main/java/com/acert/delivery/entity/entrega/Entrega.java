package com.acert.delivery.entity.entrega;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "entregas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Entrega implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String cidade;

	@Column(nullable = false)
	private String estado;

	@Column(nullable = false)
	private String bairro;

	@Column(nullable = false)
	private String cep;

	@Column(nullable = false)
	private String numero;

	@Column
	private String complemento;

//	@OneToOne(mappedBy = "entrega")
//	private Pedido pedido;
}
