package br.com.programadoremjava.s2.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.junit.Test;

import br.com.programadoremjava.s2.db.EntityManagerFactory;
import br.com.programadoremjava.s2.entity.Mapa;
import br.com.programadoremjava.s2.entity.Segmento;

public class MapaDAOTest {

	private static final EntityManager MANAGER = EntityManagerFactory.getEntityManager();
	private static final MapaDAO DAO = new MapaDAO();

	@Test
	public void testPut() {
		Mapa mapa1 = new Mapa();
		mapa1.setNome("São Paulo");

		Segmento segmento1 = new Segmento();
		segmento1.setMapa(mapa1);
		segmento1.setOrigem("São Paulo");
		segmento1.setDestino("Santos");
		segmento1.setDistancia(72l);
		mapa1.getSegmentos().add(segmento1);

		Segmento segmento2 = new Segmento();
		segmento2.setMapa(mapa1);
		segmento2.setOrigem("São Paulo");
		segmento2.setDestino("Araraquara");
		segmento2.setDistancia(278l);
		mapa1.getSegmentos().add(segmento2);

		Segmento segmento3 = new Segmento();
		segmento3.setMapa(mapa1);
		segmento3.setOrigem("São Paulo");
		segmento3.setDestino("São Sebastião");
		segmento3.setDistancia(198l);
		mapa1.getSegmentos().add(segmento3);

		Segmento segmento4 = new Segmento();
		segmento4.setMapa(mapa1);
		segmento4.setOrigem("Santos");
		segmento4.setDestino("São Sebastião");
		segmento4.setDistancia(160l);
		mapa1.getSegmentos().add(segmento4);

		DAO.put(mapa1);

		List<Mapa> resultList = MANAGER.createQuery("SELECT m FROM Mapa m", Mapa.class).getResultList();

		assertEquals(1, resultList.size());
	}

	@Test
	public void testGetById() {
		Query query = MANAGER.createNativeQuery("INSERT INTO Mapa (mapa_id, nome) VALUES (1,'São Paulo')");
		EntityTransaction tx = MANAGER.getTransaction();
		tx.begin();
		query.executeUpdate();
		tx.commit();
		assertEquals(1l, DAO.getById(1l).getId().longValue());
	}

	@Test
	public void testGetByNome() {
		Query query = MANAGER.createNativeQuery("INSERT INTO Mapa (mapa_id, nome) VALUES (1,'São Paulo')");
		EntityTransaction tx = MANAGER.getTransaction();
		tx.begin();
		query.executeUpdate();
		tx.commit();
		assertEquals(1l, DAO.getByNome("São Paulo").getId().longValue());
	}

}
