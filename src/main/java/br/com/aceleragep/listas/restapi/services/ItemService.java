package br.com.aceleragep.listas.restapi.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aceleragep.listas.restapi.entities.ItemEntity;
import br.com.aceleragep.listas.restapi.entities.ListaEntity;
import br.com.aceleragep.listas.restapi.exceptions.NotFoundBussinessException;
import br.com.aceleragep.listas.restapi.repositories.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	@Transactional
	public ItemEntity cadastra(ItemEntity itemEntity) {
		return itemRepository.save(itemEntity);
	}
	
	public ItemEntity buscaItemPorId(Long id) {
		return itemRepository.findById(id).orElseThrow(() -> new NotFoundBussinessException("Não foi encontrado o item com o id: " + id));
	}
	
	@Transactional
	public ItemEntity altera(ItemEntity itemEntity) {
		return itemRepository.save(itemEntity);
	}
	
	@Transactional
	public void remove(ItemEntity itemEntity) {
		itemRepository.delete(itemEntity);
	}
	
	@Transactional
	public ItemEntity marcaConcluido(ItemEntity itemEntity) {
		itemEntity.setConcluido(true);
		return itemRepository.save(itemEntity);
	}
	
	@Transactional
	public ItemEntity marcaNaoConcluido(ItemEntity itemEntity) {
		itemEntity.setConcluido(false);
		return itemRepository.save(itemEntity);
	}
	
	@Transactional
	public void removeItemJuntoComLista(ListaEntity listaEntity) {
		List<ItemEntity> itensEncontrados = todosItensDaLista(listaEntity);
		if(!itensEncontrados.isEmpty()) {
			for (ItemEntity itemEntity : itensEncontrados) {
				itemRepository.delete(itemEntity);
			}
		}
	}
	
	public List<ItemEntity> todosItensDaLista(ListaEntity listaEntity){
		return itemRepository.findAllByLista(listaEntity);
	}
}
