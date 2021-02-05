package dao;

import javax.persistence.EntityManager;

import model.entities.CandyDeposit;
import model.interfaces.ICandyDepositDao;
import utilities.ConEntityManager;

public class CandyDepositDao implements ICandyDepositDao {

static EntityManager em = ConEntityManager.getInstance();
	
	public void createCandyDeposit(CandyDeposit candyDeposit) {
		em.getTransaction().begin();
		em.persist(candyDeposit);
		em.getTransaction().commit();
	}
}
