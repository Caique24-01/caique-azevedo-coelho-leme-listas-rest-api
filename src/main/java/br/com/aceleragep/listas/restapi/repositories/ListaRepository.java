package br.com.aceleragep.listas.restapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.aceleragep.listas.restapi.entities.ListaEntity;

@Repository
public interface ListaRepository extends JpaRepository<ListaEntity, Long>{

}
