package br.com.aceleragep.listas.restapi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.aceleragep.listas.restapi.converts.ItemConvert;
import br.com.aceleragep.listas.restapi.converts.ListaConvert;
import br.com.aceleragep.listas.restapi.dto.inputs.ListaInput;
import br.com.aceleragep.listas.restapi.dto.output.ItemOutput;
import br.com.aceleragep.listas.restapi.dto.output.ListaOutput;
import br.com.aceleragep.listas.restapi.entities.ItemEntity;
import br.com.aceleragep.listas.restapi.entities.ListaEntity;
import br.com.aceleragep.listas.restapi.services.ItemService;
import br.com.aceleragep.listas.restapi.services.ListaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Lista")
@RestController
@RequestMapping("api/listas")
@CrossOrigin("*")
public class ListaController {

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ListaService listaService;
	
	@Autowired
	private ListaConvert listaConvert;
	
	@Autowired
	private ItemConvert itemConvert;
	
	@Operation(summary = "Cadastrar nova lista", description = "Cadastrar nova lista")
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ListaOutput cadastaLista(@Parameter(description = "Informações para o cadastro de uma nova lista") @RequestBody @Valid ListaInput listaInput) {
		ListaEntity listaEntity = listaConvert.inputToEntity(listaInput);
		ListaEntity listaCadastrada = listaService.cadastra(listaEntity);
		return listaConvert.entityToOutput(listaCadastrada);
	}
	
	@Operation(summary = "Alterar lista", description = "Alterar informações da lista")
	@PutMapping("/{id}")
	public ListaOutput alteraLista(@Parameter(description = "Id da lista", example = "1") @PathVariable Long id, @Parameter(description = "Informações para a alteração de uma lista") @RequestBody @Valid ListaInput listaInput) {
		ListaEntity listaEncontrada = listaService.buscaPorId(id);
		listaConvert.copyInputToEntity(listaInput, listaEncontrada);
		ListaEntity listaAlterada = listaService.altera(listaEncontrada);
		return listaConvert.entityToOutput(listaAlterada);
	}
	
	@Operation(summary = "Buscar uma lista", description = "Buscar uma lista pelo id da lista")
	@GetMapping("/{id}")
	public ListaOutput buscaListaPorId(@Parameter(description = "Id da lista", example = "1") @PathVariable Long id) {
		ListaEntity listaEncontrada = listaService.buscaPorId(id);
		return listaConvert.entityToOutput(listaEncontrada);
	}
	
	@Operation(summary = "Buscar todas as listas", description = "Buscar todas as listas")
	@GetMapping
	public List<ListaOutput> listaTodasListas(){
		List<ListaEntity> listaComTodasListas = listaService.listarTodas();
		return listaConvert.listEntityToOutput(listaComTodasListas);
	}
	
	@Operation(summary = "Buscar todos os itens da lista", description = "Buscar todos os itens da lista pelo id da lista")
	@GetMapping("/{idLista}/itens")
	public List<ItemOutput> listaTodosItemDaLista(@Parameter(description = "Id da lista", example = "1") @PathVariable Long idLista){
		ListaEntity listaEncontrada = listaService.buscaPorId(idLista);
		List<ItemEntity> listaComTodosItens = itemService.todosItensDaLista(listaEncontrada);
		return itemConvert.listEntityToOutput(listaComTodosItens);
	}
	
	@Operation(summary = "Deletar lista", description = "Deletar lista e junto com todos os itens vinculado a lista")
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void removeLista(@Parameter(description = "Id da lista", example = "1") @PathVariable Long id) {
		ListaEntity listaEntity = listaService.buscaPorId(id);
		itemService.removeItemJuntoComLista(listaEntity);
		listaService.remove(listaEntity);
	}
}
