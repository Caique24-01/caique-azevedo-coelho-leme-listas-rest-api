package br.com.aceleragep.listas.restapi.converts;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.aceleragep.listas.restapi.dto.inputs.ListaInput;
import br.com.aceleragep.listas.restapi.dto.output.ListaOutput;
import br.com.aceleragep.listas.restapi.entities.ListaEntity;

@Component
public class ListaConvert {

	@Autowired
	private ModelMapper modelMapper;
	
	public ListaEntity inputToEntity(ListaInput listaInput) {
		return modelMapper.map(listaInput, ListaEntity.class);
	}
	
	public ListaOutput entityToOutput(ListaEntity listaEntity) {
		return modelMapper.map(listaEntity, ListaOutput.class);
	}
	
	public void copyInputToEntity(ListaInput listaInput, ListaEntity listaEntity) {
		modelMapper.map(listaInput, listaEntity);
	}
	
	public List<ListaOutput> listEntityToOutput(List<ListaEntity> listaEntities) {
		return listaEntities.stream().map(lista -> this.entityToOutput(lista)).collect(Collectors.toList());
	}
}
