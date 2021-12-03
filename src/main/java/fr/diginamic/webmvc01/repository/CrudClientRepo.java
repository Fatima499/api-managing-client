package fr.diginamic.webmvc01.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.diginamic.webmvc01.entities.Client;
import fr.diginamic.webmvc01.entities.Emprunt;

public interface CrudClientRepo extends CrudRepository<Client,Integer> {

	@Query("select e from Emprunt e where e.clientE.id = :id")
	 public Iterable<Emprunt> findByEmprunt(int id);
}
