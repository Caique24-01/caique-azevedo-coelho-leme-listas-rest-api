package br.com.aceleragep.listas.restapi.dto.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemOutput {

	private Long id;
	private String titulo;
	private boolean concluido;
	private ListaOutput lista;
}
