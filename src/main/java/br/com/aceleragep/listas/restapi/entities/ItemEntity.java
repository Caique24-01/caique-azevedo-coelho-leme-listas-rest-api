package br.com.aceleragep.listas.restapi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_itens")
@Getter
@Setter
public class ItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "titulo", length = 100, nullable = false)
	private String titulo;
	
	@Column(name = "concluido")
	private boolean concluido;
	
	@ManyToOne
	@JoinColumn(name = "lista_id")
	private ListaEntity lista;
}
