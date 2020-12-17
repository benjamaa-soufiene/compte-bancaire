package fr.orsys.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.orsys.model.AppUser;


public interface UserRepository extends JpaRepository<AppUser, Long>{
 public AppUser findByUsername(String username);
}
