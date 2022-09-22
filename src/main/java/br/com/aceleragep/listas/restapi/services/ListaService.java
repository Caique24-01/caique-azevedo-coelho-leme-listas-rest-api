package br.com.aceleragep.listas.restapi.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aceleragep.listas.restapi.entities.ListaEntity;
import br.com.aceleragep.listas.restapi.exceptions.BadRequestBussinessException;
import br.com.aceleragep.listas.restapi.exceptions.NotFoundBussinessException;
import br.com.aceleragep.listas.restapi.repositories.ListaRepository;

@Service
public class ListaService {

	@Autowired 
	private ListaRepository listaRepository;
	
	@Transactional
	public ListaEntity cadastra(ListaEntity listaEntity) {
		return listaRepository.save(listaEntity);
	}
	
	@Transactional
	public ListaEntity altera(ListaEntity listaEntity) {
		
		if (listaEntity.getId() == null || listaEntity.getId() <= 0) {
			throw new BadRequestBussinessException("O campo Id é obrigatório para alterar uma lista!");
		}
		return listaRepository.save(listaEntity);
	}
	
	@Transactional
	public void remove(ListaEntity listaEntity) {
		listaRepository.delete(listaEntity);
	}

	public ListaEntity buscaPorId(Long id) {
		return listaRepository.findById(id).orElseThrow(() -> new NotFoundBussinessException("Não foi encontrado a lista com o id: " + id));
	}

	public List<ListaEntity> listarTodas(){
		return listaRepository.findAll();
	}
	
}
