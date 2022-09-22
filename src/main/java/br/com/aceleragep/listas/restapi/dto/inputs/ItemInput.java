package br.com.aceleragep.listas.restapi.dto.inputs;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemInput {

	@NotBlank(message = "O título é obrigatório")
	@Length(message = "O título deve ter no mínimo 1 caracter e no máximo 100 carecteres", min = 1, max = 100)
	private String titulo;
	
	@NotNull(message = "O id da lista é obrigatório")
	private Long listaId;
}
