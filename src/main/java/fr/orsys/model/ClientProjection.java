package fr.orsys.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = { Client.class }, name = "clm")
public interface ClientProjection {
	@Value("#{ target.nom + ': ' + target.email }")
	String getClientDetails();

}
