package fr.orsys.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import fr.orsys.model.AppRole;

public interface RoleRepository extends JpaRepository<AppRole, Long>{
 public AppRole findRoleByRoleName(String rolename);
}
