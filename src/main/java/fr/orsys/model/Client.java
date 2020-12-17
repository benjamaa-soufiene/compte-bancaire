package fr.orsys.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@NoArgsConstructor @AllArgsConstructor  @Data
public class Client implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;
	private String nom;
	private String email;
	@OneToMany(mappedBy = "client", fetch=FetchType.LAZY)
	private Collection<Compte> comptes;
}
