package com.acert.delivery.entity.entrega;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
