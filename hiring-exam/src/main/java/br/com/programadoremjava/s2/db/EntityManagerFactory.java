package br.com.programadoremjava.s2.db;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerFactory {

	private static class EntityManagerHolder {
		private static final EntityManager MANAGER = Persistence.createEntityManagerFactory("s2").createEntityManager();
	}

	private EntityManagerFactory() {
	}

	public static EntityManager getEntityManager() {
		return EntityManagerHolder.MANAGER;
	}

}
