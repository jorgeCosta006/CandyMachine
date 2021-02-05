package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.entities.Candy;
import model.interfaces.ICandyDao;
import utilities.ConEntityManager;

public class CandyDao implements ICandyDao {
	static EntityManager em = ConEntityManager.getInstance();
	
	//We don't remove candies, we change isActive to false;
	public void createOrUpdateCandy(Candy candy) {
		em.getTransaction().begin();
		em.persist(candy);
		em.getTransaction().commit();
	}
	
	public Candy findCandyById(int candyId) {
		Query q = em.createQuery("Select p from Candy p where candyId = " + candyId + "");
		if(q.getResultList().isEmpty())
			return null;
		
		Candy candy = (Candy) q.getSingleResult();
		return candy;
	}
	
	public List<Candy> listOfCandies() {
		Query q = em.createQuery("Select p from Candy p where isActive = " + 1);
		List<Candy> list = q.getResultList();
		return list;
	}
}
