package br.com.aceleragep.listas.restapi.converts;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.aceleragep.listas.restapi.dto.inputs.ItemInput;
import br.com.aceleragep.listas.restapi.dto.output.ItemOutput;
import br.com.aceleragep.listas.restapi.entities.ItemEntity;

@Component
public class ItemConvert {

	@Autowired
	private ModelMapper modelMapper;
	
	public ItemEntity inputToEntity(ItemInput itemInput) {
		return modelMapper.map(itemInput, ItemEntity.class);
	}
	
	public ItemOutput entityToOutput(ItemEntity itemEntity) {
		return modelMapper.map(itemEntity, ItemOutput.class);
	}
	
	public void copyInputToEntity(ItemInput itemInput, ItemEntity itemEntity) {
		modelMapper.map(itemInput, itemEntity);
	}
	
	public List<ItemOutput> listEntityToOutput(List<ItemEntity> itensEntities) {
		return itensEntities.stream().map(item -> this.entityToOutput(item)).collect(Collectors.toList());
	}
}
