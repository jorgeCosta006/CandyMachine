package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.entities.MoneyMovement;
import model.interfaces.IMoneyMovementDao;
import utilities.ConEntityManager;

public class MoneyMovementDao implements IMoneyMovementDao {
	
	public void createOrUpdateMoneyMovent(MoneyMovement moneyMovement) {
		EntityManager em = ConEntityManager.getInstance();
		em.getTransaction().begin();
		em.persist(moneyMovement);
		em.getTransaction().commit();
	}
	
	public List<MoneyMovement> moneyMovementByMachineId(int machineId) {
		EntityManager em = ConEntityManager.getInstance();
		Query query = em.createQuery("select p from MoneyMovement p where machineId = " + machineId);
		Boolean notExist = query.getResultList().isEmpty();
		if (notExist)
			return null;

		List<MoneyMovement> moneyMovement = query.getResultList();
		return moneyMovement;
	}
}
