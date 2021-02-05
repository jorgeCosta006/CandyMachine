package dao;

import javax.persistence.EntityManager;

import model.entities.Purchase;
import model.interfaces.IPurchaseDao;
import utilities.ConEntityManager;

public class PurchaseDao implements IPurchaseDao {
	static EntityManager em = ConEntityManager.getInstance();
	
	public void createPurchase(Purchase purchase) {
		em.getTransaction().begin();
		em.persist(purchase);
		em.getTransaction().commit();
	}
	
}
