package fr.orsys.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.orsys.model.Client;

@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Long> {	
}
