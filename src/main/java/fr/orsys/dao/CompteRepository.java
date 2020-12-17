package fr.orsys.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import fr.orsys.model.Compte;
import io.swagger.annotations.ApiOperation;

@RepositoryRestResource
public interface CompteRepository extends JpaRepository<Compte, String> {
	@ApiOperation("rechercher un compte client par son code")
	Compte findByCodeCompte(String codeCpte);
}
