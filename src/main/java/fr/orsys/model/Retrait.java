package fr.orsys.model;


import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("R")
@Data @NoArgsConstructor  
public class Retrait extends Operation{
	private static final long serialVersionUID = 1L;
	public Retrait(Date dateOperation, double montant, Compte compte) {
		super(dateOperation, montant, compte);
	}
}
