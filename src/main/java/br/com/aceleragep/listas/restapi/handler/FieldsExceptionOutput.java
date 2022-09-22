package br.com.aceleragep.listas.restapi.handler;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FieldsExceptionOutput {

	private String name;
	private String message;

}