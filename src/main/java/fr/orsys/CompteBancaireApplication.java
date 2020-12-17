package fr.orsys;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fr.orsys.dao.ClientRepository;
import fr.orsys.dao.CompteRepository;
import fr.orsys.dao.OperationRepository;
import fr.orsys.model.AppRole;
import fr.orsys.model.AppUser;
import fr.orsys.model.Client;
import fr.orsys.model.Compte;
import fr.orsys.model.CompteCourant;
import fr.orsys.model.CompteEpargne;
import fr.orsys.model.Operation;
import fr.orsys.model.Retrait;
import fr.orsys.model.Versements;
import fr.orsys.service.AccountService;

@SpringBootApplication
public class CompteBancaireApplication implements ApplicationRunner {
	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		repositoryRestConfiguration.exposeIdsFor(Client.class, Compte.class, Operation.class);
	}

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(CompteBancaireApplication.class, args);
		ClientRepository clientRepository = ctx.getBean(ClientRepository.class);
		OperationRepository operationRepository = ctx.getBean(OperationRepository.class);
		CompteRepository compteRepository = ctx.getBean(CompteRepository.class);
		System.out.println("--------Ajout des Clients---------");
		Client client1 = new Client();
		client1.setNom("claude");
		client1.setEmail("claude@gmail.com");
		clientRepository.save(client1);
		Client client2 = new Client();
		client2.setNom("Fabian");
		client2.setEmail("fabien@gmail.com");
		clientRepository.save(client2);
		clientRepository.findAll().forEach(c -> {
			System.out.println(c.getNom());
		});
		System.out.println("--------Ajout des Comptes---------");
		Compte cp1 = compteRepository.save(new CompteCourant("c1", new Date(), 90000, client1, 6000));
		Compte cp2 = compteRepository.save(new CompteEpargne("c2", new Date(), 6000, client2, 5.5));
		System.out.println("--------Ajout des opérations---------");
		operationRepository.save(new Versements(new Date(), 9000, cp1));
		operationRepository.save(new Versements(new Date(), 6000, cp1));
		operationRepository.save(new Versements(new Date(), 2300, cp1));
		operationRepository.save(new Retrait(new Date(), 9000, cp1));
		operationRepository.save(new Versements(new Date(), 2300, cp2));
		operationRepository.save(new Versements(new Date(), 6000, cp2));
		operationRepository.save(new Versements(new Date(), 2000, cp2));
		operationRepository.save(new Retrait(new Date(), 3000, cp2));

		AccountService accountService = ctx.getBean(AccountService.class);
		accountService.saveUser(new AppUser(null, "admin", "1234", null));
		accountService.saveUser(new AppUser(null, "manager", "1234", null));
		accountService.saveUser(new AppUser(null, "user", "1234", null));
		accountService.saveRole(new AppRole(null, "ADMIN"));
		accountService.saveRole(new AppRole(null, "MANAGER"));
		accountService.saveRole(new AppRole(null, "USER"));
		accountService.addRoleToUser("admin", "ADMIN");
		accountService.addRoleToUser("manager", "MANAGER");
		accountService.addRoleToUser("user", "USER");
	}

	@Bean
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}
}
