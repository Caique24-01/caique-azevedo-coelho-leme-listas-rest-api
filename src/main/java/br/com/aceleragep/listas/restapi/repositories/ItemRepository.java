package br.com.aceleragep.listas.restapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.aceleragep.listas.restapi.entities.ItemEntity;
import br.com.aceleragep.listas.restapi.entities.ListaEntity;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

	List<ItemEntity> findAllByLista(ListaEntity lista);
}
