package br.com.programadoremjava.s2.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import br.com.programadoremjava.s2.db.EntityManagerFactory;
import br.com.programadoremjava.s2.entity.Mapa;

/**
 * Classe para gerenciar os Mapas no DB
 * 
 * @author Bruno Teofilo da Silva
 */
public class MapaDAO {

	/**
	 * Cria um novo mapa ou atualiza um mapa existente;
	 * 
	 * @param mapa
	 *            {@link Mapa} a ser persistido no DB.
	 * @return o {@link Mapa} atualizado após ser persistido.
	 */
	public Mapa put(Mapa mapa) {
		if (null == mapa.getId()) {
			return save(mapa);
		} else {
			return update(mapa);
		}
	}

	private Mapa update(Mapa mapa) {
		EntityManager manager = EntityManagerFactory.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		manager.merge(mapa);
		tx.commit();
		return mapa;
	}

	private Mapa save(Mapa mapa) {
		EntityManager manager = EntityManagerFactory.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		manager.persist(mapa);
		tx.commit();
		return mapa;
	}

	/**
	 * Recupera um mapa utilizando o seu ID
	 * 
	 * @param id
	 *            ID do mapa a ser recuperado do DB
	 * @return o {@link Mapa} com o ID informado
	 * @throws EntityNotFoundException
	 *             caso o id não exista no DB.
	 */
	public Mapa getById(Long id) {
		EntityManager manager = EntityManagerFactory.getEntityManager();
		Mapa mapa = manager.find(Mapa.class, id);
		if (null != mapa) {
			return mapa;
		} else {
			throw new EntityNotFoundException(String.format("Não foi possível encontrar um mapa com o id '%d'.", id));
		}

	}

	/**
	 * Recupera um mapa utilizando o seu Nome
	 * 
	 * @param nome
	 *            Nome do mapa a ser encontrado no DB.
	 * @return o {@link Mapa} com o nome informado, ou <code>null</code> caso
	 *         não exista.
	 * @throws Pode
	 *             lançar {@link NonUniqueResultException} se mais de uma mapa
	 *             com o mesmo nome existir no DB.
	 */
	public Mapa findByNome(String nome) {
		EntityManager manager = EntityManagerFactory.getEntityManager();
		TypedQuery<Mapa> query = manager.createQuery("SELECT m FROM Mapa m WHERE m.nome=:nome", Mapa.class);
		query.setParameter("nome", nome);
		List<Mapa> resultList = query.getResultList();
		if (resultList.size() == 0) {
			return null;
		} else if (resultList.size() == 1) {
			return resultList.get(0);
		} else {
			throw new NonUniqueResultException();
		}
	}

}
