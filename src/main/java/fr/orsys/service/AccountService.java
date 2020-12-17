package fr.orsys.service;

import fr.orsys.model.AppRole;
import fr.orsys.model.AppUser;

public interface AccountService {
public AppUser saveUser(AppUser user);
public AppRole saveRole(AppRole role);
public void addRoleToUser(String username, String roleName);
public AppUser findUserByUsername(String username);
}
