package br.com.programadoremjava.s2.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Test;

import br.com.programadoremjava.s2.db.EntityManagerFactory;
import br.com.programadoremjava.s2.entity.Mapa;
import br.com.programadoremjava.s2.entity.Segmento;

public class MapaDAOTest {

	@Test
	public void testPut() {

		// Instanciando a DAO e o EntityManager
		final MapaDAO DAO = new MapaDAO();
		final EntityManager MANAGER = EntityManagerFactory.getEntityManager();

		// Criando um mapa para ser persistido utilizando a DAO
		Mapa mapa = new Mapa();
		mapa.setNome("São Paulo");

		Segmento segmento1 = new Segmento();
		segmento1.setMapa(mapa);
		segmento1.setOrigem("São Paulo");
		segmento1.setDestino("Santos");
		segmento1.setDistancia(72l);
		mapa.getSegmentos().add(segmento1);

		Segmento segmento2 = new Segmento();
		segmento2.setMapa(mapa);
		segmento2.setOrigem("São Paulo");
		segmento2.setDestino("Araraquara");
		segmento2.setDistancia(278l);
		mapa.getSegmentos().add(segmento2);

		Segmento segmento3 = new Segmento();
		segmento3.setMapa(mapa);
		segmento3.setOrigem("São Paulo");
		segmento3.setDestino("São Sebastião");
		segmento3.setDistancia(198l);
		mapa.getSegmentos().add(segmento3);

		Segmento segmento4 = new Segmento();
		segmento4.setMapa(mapa);
		segmento4.setOrigem("Santos");
		segmento4.setDestino("São Sebastião");
		segmento4.setDistancia(160l);
		mapa.getSegmentos().add(segmento4);

		// Chamando a DAO para fazer a persistência
		DAO.put(mapa);
		assertNotNull(mapa.getId());

		// Recuperando e testando se o objeto foi persistido
		TypedQuery<Mapa> query = MANAGER.createQuery("SELECT m FROM Mapa m WHERE m.id=:id", Mapa.class);
		query.setParameter("id", mapa.getId());
		List<Mapa> resultList = query.getResultList();
		assertEquals(1, resultList.size());

		Mapa mapaFromDB = resultList.get(0);
		assertEquals("São Paulo", mapaFromDB.getNome());
		assertEquals(4, mapaFromDB.getSegmentos().size());
		assertEquals("São Paulo", mapaFromDB.getSegmentos().get(0).getOrigem());
		assertEquals("Santos", mapaFromDB.getSegmentos().get(0).getDestino());
		assertEquals(72l, mapaFromDB.getSegmentos().get(0).getDistancia().longValue());

		// Atualizando o mapa recuperado do DB para verificar se a DAO o
		// atualiza
		Segmento segmento5 = new Segmento();
		segmento5.setMapa(mapa);
		segmento5.setOrigem("Santos");
		segmento5.setDestino("Ubatuba");
		segmento5.setDistancia(232l);
		mapaFromDB.getSegmentos().add(segmento5);
		mapaFromDB.setNome("SP");

		// Chamando a DAO para atualizar o DB
		DAO.put(mapaFromDB);
		assertEquals(mapa.getId(), mapaFromDB.getId());

		// Confirmando que não foi criado um novo mapa no DB
		TypedQuery<Mapa> updatedQuery = MANAGER.createQuery("SELECT m FROM Mapa m WHERE m.id=:id", Mapa.class);
		updatedQuery.setParameter("id", mapa.getId());
		List<Mapa> updatedResultList = updatedQuery.getResultList();
		assertEquals(1, updatedResultList.size());

		// Confirmando que o mapa realmente foi atualizado.
		Mapa updatedMapa = updatedResultList.get(0);
		assertEquals("SP", updatedMapa.getNome());
		assertEquals(5, updatedMapa.getSegmentos().size());

	}

	@Test(expected = EntityNotFoundException.class)
	public void testGetById() {
		// Instanciando a DAO e o EntityManager
		final MapaDAO DAO = new MapaDAO();
		final EntityManager MANAGER = EntityManagerFactory.getEntityManager();

		// Criando um mapa no DB
		Query query = MANAGER.createNativeQuery("INSERT INTO Mapa (mapa_id, nome) VALUES (13579,'São Paulo')");
		EntityTransaction tx = MANAGER.getTransaction();
		tx.begin();
		query.executeUpdate();
		tx.commit();
		// Criando alguns seguimentos no mapa
		query = MANAGER.createNativeQuery("INSERT INTO Segmento (mapa_id, origem, destino, distancia) VALUES " //
				+ "(13579,'São Paulo','Santos',72)," //
				+ "(13579,'São Paulo','Araraquara',278)," //
				+ "(13579,'São Paulo','São Sebastião',198)," //
				+ "(13579,'Santos','São Sebastião',160)");
		tx = MANAGER.getTransaction();
		tx.begin();
		query.executeUpdate();
		tx.commit();

		// Recuperando o mapa criando usando a DAO
		Mapa mapa = DAO.getById(13579l);
		assertNotNull(mapa);
		assertEquals(13579l, mapa.getId().longValue());
		assertEquals("São Paulo", mapa.getNome());
		assertEquals(4, mapa.getSegmentos().size());

		// Tentando recuperar um mapa que não existe
		DAO.getById(24680l);
	}

	@Test
	public void testFindByNome() {
		// Instanciando a DAO e o EntityManager
		final MapaDAO DAO = new MapaDAO();
		final EntityManager MANAGER = EntityManagerFactory.getEntityManager();

		// Criando um mapa no DB
		Query query = MANAGER.createNativeQuery("INSERT INTO Mapa (mapa_id, nome) VALUES (97531,'S.Paulo')");
		EntityTransaction tx = MANAGER.getTransaction();
		tx.begin();
		query.executeUpdate();
		tx.commit();
		// Criando alguns seguimentos no mapa
		query = MANAGER.createNativeQuery("INSERT INTO Segmento (mapa_id, origem, destino, distancia) VALUES " //
				+ "(97531,'São Paulo','Santos',72)," //
				+ "(97531,'São Paulo','Araraquara',278)," //
				+ "(97531,'São Paulo','São Sebastião',198)," //
				+ "(97531,'Santos','São Sebastião',160)");
		tx = MANAGER.getTransaction();
		tx.begin();
		query.executeUpdate();
		tx.commit();

		// Procurando o mapa criando usando a DAO
		Mapa mapa = DAO.findByNome("S.Paulo");
		assertNotNull(mapa);
		assertEquals(97531l, mapa.getId().longValue());
		assertEquals("S.Paulo", mapa.getNome());
		assertEquals(4, mapa.getSegmentos().size());

		// Procurando um mapa que não existe
		Mapa mapaNaoExistente = DAO.findByNome("Rio de Janeiro");
		assertNull(mapaNaoExistente);

	}

}
