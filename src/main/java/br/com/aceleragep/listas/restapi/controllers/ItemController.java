package br.com.aceleragep.listas.restapi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.aceleragep.listas.restapi.converts.ItemConvert;
import br.com.aceleragep.listas.restapi.dto.inputs.ItemInput;
import br.com.aceleragep.listas.restapi.dto.output.ItemOutput;
import br.com.aceleragep.listas.restapi.entities.ItemEntity;
import br.com.aceleragep.listas.restapi.entities.ListaEntity;
import br.com.aceleragep.listas.restapi.services.ItemService;
import br.com.aceleragep.listas.restapi.services.ListaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Item")
@RestController
@RequestMapping("/api/itens")
@CrossOrigin("*")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ListaService listaService;
	
	@Autowired
	private ItemConvert itemConvert;
	
	@Operation(summary = "Cadastrar novo item", description = "Cadastrar novo item em uma lista")
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ItemOutput cadastraItem(@Parameter(description = "Informações para o cadastro de um novo item") @RequestBody @Valid ItemInput itemInput) {
		ItemEntity itemEntity = itemConvert.inputToEntity(itemInput);
		convertListaId(itemInput, itemEntity);
		itemEntity.setConcluido(false);
		ItemEntity itemCadastrado = itemService.cadastra(itemEntity);
		return itemConvert.entityToOutput(itemCadastrado);
	}
	
	@Operation(summary = "Alterar item", description = "Alterar informações do item")
	@PutMapping("/{id}")
	public ItemOutput alteraItem(@Parameter(description = "Id do item", example = "1") @PathVariable Long id, @Parameter(description = "Informações para a alteração de um item") @RequestBody @Valid ItemInput itemInput) {
		ItemEntity itemEncontrado = itemService.buscaItemPorId(id);
		itemConvert.copyInputToEntity(itemInput, itemEncontrado);
		convertListaId(itemInput, itemEncontrado);
		ItemEntity itemAlterado = itemService.altera(itemEncontrado);
		return itemConvert.entityToOutput(itemAlterado);
	}
	
	@Operation(summary = "Deletar item", description = "Deletar item da lista")
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void removerItem(@Parameter(description = "Id do item", example = "1") @PathVariable Long id) {
		ItemEntity itemEncontrado = itemService.buscaItemPorId(id);
		itemService.remove(itemEncontrado);
	}
	
	@Operation(summary = "Marcar item concluído", description = "Marcar item como concluído")
	@PutMapping("/{id}/marca-concluido")
	public ItemOutput marcaItemConcluido(@Parameter(description = "Id do item", example = "1") @PathVariable Long id) {
		ItemEntity itemEntity = itemService.buscaItemPorId(id);
		ItemEntity itemAlterado = itemService.marcaConcluido(itemEntity);
		return itemConvert.entityToOutput(itemAlterado);
	}
	
	@Operation(summary = "Marcar item não concluído", description = "Marcar item como não concluído")
	@PutMapping("/{id}/marca-nao-concluido")
	public ItemOutput marcaItemNaoConcluido(@Parameter(description = "Id do item", example = "1") @PathVariable Long id) {
		ItemEntity itemEntity = itemService.buscaItemPorId(id);
		ItemEntity itemAlterado = itemService.marcaNaoConcluido(itemEntity);
		return itemConvert.entityToOutput(itemAlterado);
	}
	
	public void convertListaId(ItemInput itemInput, ItemEntity itemEntity) {
		ListaEntity listaEncontrada = listaService.buscaPorId(itemInput.getListaId());
		itemEntity.setLista(listaEncontrada);
	}
}
