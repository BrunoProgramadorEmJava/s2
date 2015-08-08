package br.com.programadoremjava.s2.db;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Test;

public class EntityManagerFactoryTest {

	@Test
	public void testGetEntityManager() {
		EntityManager manager = EntityManagerFactory.getEntityManager();
		assertNotNull(manager);
		EntityManager otherManager = EntityManagerFactory.getEntityManager();
		assertSame(manager, otherManager);
	}
	
}
