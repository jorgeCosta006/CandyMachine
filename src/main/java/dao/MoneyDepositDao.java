package dao;

import javax.persistence.EntityManager;

import model.entities.MoneyDeposit;
import model.interfaces.IMoneyDepositDao;
import utilities.ConEntityManager;

public class MoneyDepositDao implements IMoneyDepositDao {
static EntityManager em = ConEntityManager.getInstance();
	
	public void createMoneyDeposit(MoneyDeposit moneyDeposit) {
		em.getTransaction().begin();
		em.persist(moneyDeposit);
		em.getTransaction().commit();
	}
}
