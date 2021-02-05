package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.entities.History;
import model.interfaces.IHistoryDao;
import utilities.ConEntityManager;

public class HistoryDao implements IHistoryDao {

	public void newHistory(History history) {
		EntityManager em = ConEntityManager.getInstance();

		em.getTransaction().begin();
		em.persist(history);
		em.getTransaction().commit();
	}

	public List<History> getHistoryList(int userId) {
		EntityManager em = ConEntityManager.getInstance();

		Query q = em.createQuery("Select p from History p where userId = '" + userId + "'");
		List<History> list = q.getResultList();
		return list;
	}
	
	public List<History> getCandyDepositHistoryList(int userId) {
		EntityManager em = ConEntityManager.getInstance();

		Query q = em.createQuery("Select p from History p where userId = '" + userId + "' and movimento = 'Candy Deposit:'");
		List<History> list = q.getResultList();
		return list;
	}
	
	public List<History> getMoneyDepositHistoryList(int userId) {
		EntityManager em = ConEntityManager.getInstance();

		Query q = em.createQuery("Select p from History p where userId = '" + userId + "' and movimento = 'Money Deposit:'");
		List<History> list = q.getResultList();
		return list;
	}
}
