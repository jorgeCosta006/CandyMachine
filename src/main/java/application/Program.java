package application;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Program {

	public static void main(String[] args) {

		EntityManagerFactory factory;
		String PERSISTENCE_UNIT_NAME = "candyMachineDB";
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();

//		MainPageBusiness mpb = new MainPageBusiness();
//		mpb.buyCandy(2, 10, 4);

//		UserDao.addClientUser("jo", "be", "co");
//		UserDao.changeClientUser(1, "done", "done", "co");

//		HistoryBusiness hb = new HistoryBusiness();
//		List<History> list = hb.returnHistoryList(2);
//		for (History line : list) {
//			System.out.println(line.getMovimento());
//		}
		
//		MoneyBusiness mb = new MoneyBusiness();
//		mb.moneyDeposit(3, 1, 2, 2, 1, 1, 1, 1);
//		AmountOfCoins aoc = mb.giveChange(1.50);
//		if(aoc == null)
//			System.out.println("No enough money to give the change");
//		
//		System.out.println(aoc.getCoins5Cent());
//		System.out.println(aoc.getCoins10Cent());
//		System.out.println(aoc.getCoins20Cent());
//		System.out.println(aoc.getCoins50Cent());
//		System.out.println(aoc.getCoins1Euro());
//		System.out.println(aoc.getCoins2Euro());
	}

}
