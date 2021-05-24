package fravega.ncrivelli.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fravega.ncrivelli.modelo.Nodo;

@Repository
public interface INodo extends CrudRepository<Nodo, Integer> {

}
