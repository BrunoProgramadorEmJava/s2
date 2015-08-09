package br.com.programadoremjava.s2.db;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerFactory {

	private static EntityManager manager;
	
	private EntityManagerFactory() {
	}

	public static EntityManager getEntityManager() {
		if (null == manager) {
			synchronized (EntityManagerFactory.class) {
				if (null == manager) {
					javax.persistence.EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("s2");
					manager = entityManagerFactory.createEntityManager();
				}
			}
		}
		return manager;
	}

}
