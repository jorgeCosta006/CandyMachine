package model.business;

import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;

import dao.CandyDao;
import dao.CandyDepositDao;
import dao.HistoryDao;
import dao.PurchaseDao;
import model.entities.Candy;
import model.entities.CandyDeposit;
import model.entities.History;
import model.entities.Machine;
import model.entities.Purchase;
import model.entities.User;
import model.interfaces.ICandyDao;
import model.interfaces.ICandyDepositDao;
import model.interfaces.IHistoryDao;
import model.interfaces.IPurchaseDao;

public class MainPageBusiness {

	public List<Candy> returnCandyList() {
		ICandyDao cd = new CandyDao();
		return cd.listOfCandies();
	}

	public boolean buyCandy(User user, Candy candy, int numberOfCandies) {
		ICandyDao cd = new CandyDao();
		IPurchaseDao pd = new PurchaseDao();
		IHistoryDao hd = new HistoryDao();

		Integer currentNumberOfCandies = candy.getQuantity();

		if (currentNumberOfCandies >= numberOfCandies) {
			int changedNumberOfCandies = currentNumberOfCandies - numberOfCandies;
			double totalPrice = candy.getPrice() * numberOfCandies;
			candy.setQuantity(changedNumberOfCandies);
			cd.createOrUpdateCandy(candy);

			Machine machine = (Machine) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get("loggedMachine");
			Purchase purchase = new Purchase(user, candy, numberOfCandies, totalPrice, new Date(), machine);
			pd.createPurchase(purchase);
			History history = new History("Purchase", user, purchase, null, null);
			hd.newHistory(history);

			System.out.println("Bought");
			return true;

		} else {
			System.out.println("Not enough candies in the machine");
			return false;
		}
	}

	public void removeCandy(Candy candy) {
		ICandyDao cd = new CandyDao();
		candy.setActive(false);
		cd.createOrUpdateCandy(candy);
	}

	public boolean createOrUpdateCandy(User user, Candy candy, String name, double price, int quantity) {
		ICandyDao cd = new CandyDao();

		if (candy != null) {
			candy.setQuantity(quantity);
			candy.setPrice(price);
		} else {
			List<Candy> candies = cd.listOfCandies();
			for(Candy obj : candies) {
				if(obj.getName().equalsIgnoreCase(name) && obj.isActive() == true) {
					return false;
				}
			}
			candy = new Candy(name, price, quantity, true);
		}

		cd.createOrUpdateCandy(candy);
		candyDeposit(user, candy, quantity);
		
		return true;
	}

	public void candyDeposit(User user, Candy candy, int quantity) {
		ICandyDepositDao cdd = new CandyDepositDao();
		IHistoryDao hd = new HistoryDao();

		Machine machine = (Machine) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("loggedMachine");
		CandyDeposit candyDeposit = new CandyDeposit(candy, user, quantity, new Date(), machine);
		cdd.createCandyDeposit(candyDeposit);
		History history = new History("Candy Deposit:", user, null, candyDeposit, null);
		hd.newHistory(history);
	}
}
