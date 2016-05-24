package br.com.cotacao.dao;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class JPAUtil implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private EntityManagerFactory factory;
	
	public JPAUtil(){
		factory = Persistence.createEntityManagerFactory("cotacao");
	}

	@Produces
	@RequestScoped
	public EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

	public void close(@Disposes EntityManager manager) {
		manager.close();
	}
}
