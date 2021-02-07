package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.entities.CandyMovement;
import model.interfaces.ICandyMovementDao;
import utilities.ConEntityManager;

public class CandyMovementDao implements ICandyMovementDao {

	public void createOrUpdateCandyMovement(CandyMovement candyMovement) {
		EntityManager em = ConEntityManager.getInstance();
		em.getTransaction().begin();
		em.persist(candyMovement);
		em.getTransaction().commit();
	}
	
	public List<CandyMovement> candyMovementByMachineId(int machineId) {
		EntityManager em = ConEntityManager.getInstance();
		Query query = em.createQuery("select p from CandyMovement p where machineId = " + machineId);
		Boolean notExist = query.getResultList().isEmpty();
		if (notExist)
			return null;

		List<CandyMovement> candyMovement = query.getResultList();
		return candyMovement;
	}
	
	public List<CandyMovement> candyMovementByUserId(int userId) {
		EntityManager em = ConEntityManager.getInstance();
		Query query = em.createQuery("select p from CandyMovement p where userId = " + userId);
		Boolean notExist = query.getResultList().isEmpty();
		if (notExist)
			return null;

		List<CandyMovement> candyMovement = query.getResultList();
		return candyMovement;
	}
}