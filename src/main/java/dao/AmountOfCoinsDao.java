package dao;

import javax.persistence.EntityManager;

import model.entities.AmountOfCoins;
import model.interfaces.IAmountOfCoinsDao;
import utilities.ConEntityManager;

public class AmountOfCoinsDao implements IAmountOfCoinsDao {
static EntityManager em = ConEntityManager.getInstance();
	
	public void createOrUpdateAmountOfCoins(AmountOfCoins amountOfCoins) {
		em.getTransaction().begin();
		em.persist(amountOfCoins);
		em.getTransaction().commit();
	}
}
