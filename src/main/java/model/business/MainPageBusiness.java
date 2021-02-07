package model.business;

import java.util.Date;
import java.util.List;

import dao.CandyDao;
import dao.CandyMovementDao;
import model.entities.Candy;
import model.entities.CandyMovement;
import model.entities.Machine;
import model.entities.User;
import model.interfaces.ICandyDao;
import model.interfaces.ICandyMovementDao;
/**
 * Business responsável por ações da mainPage;
 */
public class MainPageBusiness {

	public List<Candy> returnCandyList() {
		ICandyDao cd = new CandyDao();
		return cd.listOfCandies();
	}

	/**
	 * Altera a quantidade de candies e criar um candyMovement após uma compra efectuada;
	 */
	public void changeCandyQuantityAndCreateCandyMovement(User user, Candy candy, Machine machine) {
		ICandyDao cd = new CandyDao();
		ICandyMovementDao cmd = new CandyMovementDao();

		candy.setQuantity(candy.getQuantity() - 1);
		cd.createOrUpdateCandy(candy);

		CandyMovement candyMovement = new CandyMovement("Bought", candy.getName(), candy.getPrice(), 1, new Date(),
				candy, user, machine);
		cmd.createOrUpdateCandyMovement(candyMovement);

	}

	/**
	 * Sfot delete num candy e cria um registo de Removed na tabela CandyMovement;
	 */
	public void removeCandy(Candy candy, User user, Machine machine) {
		ICandyDao cd = new CandyDao();
		candy.setActive(false);
		cd.createOrUpdateCandy(candy);

		ICandyMovementDao cmd = new CandyMovementDao();
		CandyMovement candyMovement = new CandyMovement("Removed", candy.getName(), null, null, new Date(), candy, user,
				machine);
		cmd.createOrUpdateCandyMovement(candyMovement);
	}

	/**
	 * Cria ou edita um candy e cria um novo registo conforme a ação efectuada na tabela CandyMovement; Retorna true se criado ou false se já existir um com esse nome;
	 */
	public boolean createOrUpdateCandy(User user, Candy candy, Machine machine, String name, double price,
			int quantity) {
		ICandyDao cd = new CandyDao();
		ICandyMovementDao cmd = new CandyMovementDao();
		CandyMovement candyMovement = null;
		CandyMovement candyMovementPrice = null;

		if (candy != null) {
			if (quantity != candy.getQuantity()) {
				candyMovement = new CandyMovement("Updated Quantity", candy.getName(), null, quantity, new Date(),
						candy, user, machine);
				cmd.createOrUpdateCandyMovement(candyMovement);
			}

			if (price != candy.getPrice()) {
				candyMovementPrice = new CandyMovement("Updated Price", candy.getName(), price, null, new Date(), candy,
						user, machine);
				cmd.createOrUpdateCandyMovement(candyMovementPrice);
			}

			candy.setQuantity(quantity);
			candy.setPrice(price);
			cd.createOrUpdateCandy(candy);
		} else {
			List<Candy> candies = cd.listOfCandies();
			for (Candy obj : candies) {
				if (obj.getName().equalsIgnoreCase(name) && obj.isActive() == true) {
					return false;
				}
			}
			candy = new Candy(name, price, quantity, true);
			cd.createOrUpdateCandy(candy);
			candyMovement = new CandyMovement("Created", name, price, quantity, new Date(), candy, user, machine);
			cmd.createOrUpdateCandyMovement(candyMovement);
		}

		return true;
	}
}
