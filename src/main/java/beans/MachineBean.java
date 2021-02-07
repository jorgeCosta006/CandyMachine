package beans;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import model.business.MainPageBusiness;
import model.business.MoneyBusiness;
import model.entities.Candy;
import model.entities.Machine;
import model.entities.User;
import utilities.AmountOfCoins;

@ManagedBean(name = "machineBean", eager = true)
@ViewScoped
public class MachineBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Candy> candies;
	private String name;

	private Candy candy;
	private Machine machine;

	private int coins5Cent;
	private int coins10Cent;
	private int coins20Cent;
	private int coins50Cent;
	private int coins1Euro;
	private int coins2Euro;
	private double totalMoney; // Represent the money inserted by the user
	private double totalMoneyInTheMachine; // Represent the current money in the machine

	@PostConstruct
	public void list() {
		MainPageBusiness mpb = new MainPageBusiness();
		candies = mpb.returnCandyList();

		Machine machineRequest = (Machine) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("loggedMachine");
		setMachine(machineRequest);

	}

	public List<Candy> getCandies() {
		return candies;
	}

	public void setCandies(List<Candy> candies) {
		this.candies = candies;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCoins10Cent() {
		return coins10Cent;
	}

	public void setCoins10Cent(int coins10Cent) {
		this.coins10Cent = coins10Cent;
	}

	public int getCoins5Cent() {
		return coins5Cent;
	}

	public void setCoins5Cent(int coins5Cent) {
		this.coins5Cent = coins5Cent;
	}

	public int getCoins20Cent() {
		return coins20Cent;
	}

	public void setCoins20Cent(int coins20Cent) {
		this.coins20Cent = coins20Cent;
	}

	public int getCoins50Cent() {
		return coins50Cent;
	}

	public void setCoins50Cent(int coins50Cent) {
		this.coins50Cent = coins50Cent;
	}

	public int getCoins1Euro() {
		return coins1Euro;
	}

	public void setCoins1Euro(int coins1Euro) {
		this.coins1Euro = coins1Euro;
	}

	public int getCoins2Euro() {
		return coins2Euro;
	}

	public void setCoins2Euro(int coins2Euro) {
		this.coins2Euro = coins2Euro;
	}

	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public double getTotalMoneyInTheMachine() {
		Machine machine = (Machine) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("loggedMachine");
		totalMoneyInTheMachine = machine.getTotalCash();
		return totalMoneyInTheMachine;
	}

	public void setTotalMoneyInTheMachine(double totalMoneyInTheMachine) {
		this.totalMoneyInTheMachine = totalMoneyInTheMachine;
	}

	public boolean checkForEnoughMoney() {
		AmountOfCoins aoc = new AmountOfCoins(coins5Cent, coins10Cent, coins20Cent, coins50Cent, coins1Euro,
				coins2Euro);
		double clientMoney = MoneyBusiness.calculateTotal(aoc);
		double candyPrice = candy.getPrice();

		if (clientMoney < candyPrice) {
			return false;
		}

		return true;
	}

	public String buyCandy() {

		boolean candyBought = false;
		AmountOfCoins changeInAmountOfCoins = null;
		double changeValue = 0.0;

		if (checkForEnoughMoney()) {

			MoneyBusiness mb = new MoneyBusiness();
			AmountOfCoins amountOfCoinsAdded = new AmountOfCoins(coins5Cent, coins10Cent, coins20Cent, coins50Cent,
					coins1Euro, coins2Euro);

			Machine machine = (Machine) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get("loggedMachine");
			User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedUser");
			
			NumberFormat nf_in = NumberFormat.getNumberInstance(Locale.US);
			nf_in.setMaximumFractionDigits(2);
			String result = nf_in.format(totalMoney - candy.getPrice());
			changeValue = Double.valueOf(result);
			changeInAmountOfCoins = mb.giveChange(machine, amountOfCoinsAdded, changeValue);

			if (changeInAmountOfCoins != null) {
				MainPageBusiness mpb = new MainPageBusiness();
				
				mpb.changeCandyQuantityAndCreateCandyMovement(user, candy, machine);
				mb.createMoneyMovement("In", amountOfCoinsAdded, user, machine);
				if (changeValue != 0)
					mb.createMoneyMovement("Out", changeInAmountOfCoins, user, machine);

				candyBought = true;

			} else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Not enough change, try to put the right amount");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				return "";
			}
		}

		showMessage(candyBought, changeInAmountOfCoins, changeValue);
		turnCoinsToZero(null);
		return "";
	}

	public void showMessage(boolean candyBought, AmountOfCoins aoc, double changeValue) {
		FacesMessage message = null;
		if (candyBought) {
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
					"Bought! Change: " + changeValue + "\n Coins: \n 5 cent: " + aoc.getCoins5Cent() + ";\n 10 cent: "
							+ aoc.getCoins10Cent() + ";\n 20 cent: " + aoc.getCoins20Cent() + ";\n 50 cent: "
							+ aoc.getCoins50Cent() + ";\n 1 euro: " + aoc.getCoins1Euro() + ";\n 2 euro: "
							+ aoc.getCoins2Euro() + ";");
		} else {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message", "Not enough money");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}

		PrimeFaces.current().dialog().showMessageDynamic(message);
	}

	public void incrementCoin(String coin) {
		NumberFormat nf_in = NumberFormat.getNumberInstance(Locale.US);
		nf_in.setMaximumFractionDigits(2);
		switch (coin) {
		case "0.05": {
			coins5Cent += 1;
			String soma = nf_in.format(totalMoney + 0.05);
			totalMoney = Double.valueOf(soma);
		}
			break;
		case "0.10": {
			coins10Cent += 1;
			String soma = nf_in.format(totalMoney + 0.10);
			totalMoney = Double.valueOf(soma);
		}
			break;
		case "0.20": {
			coins20Cent += 1;
			String soma = nf_in.format(totalMoney + 0.20);
			totalMoney = Double.valueOf(soma);
		}
			break;
		case "0.50": {
			coins50Cent += 1;
			String soma = nf_in.format(totalMoney + 0.50);
			totalMoney = Double.valueOf(soma);
		}
			break;
		case "1.00": {
			coins1Euro += 1;
			totalMoney += 1;
		}
			break;
		case "2.00": {
			coins2Euro += 1;
			totalMoney += 2;
		}
			break;
		}
	}

	public void turnCoinsToZero(Candy candy) {
		coins5Cent = 0;
		coins10Cent = 0;
		coins20Cent = 0;
		coins50Cent = 0;
		coins1Euro = 0;
		coins2Euro = 0;
		totalMoney = 0;
		this.candy = candy;
	}

	public void depositMoney() {
		if (coins5Cent == 0 && coins10Cent == 0 && coins20Cent == 0 && coins50Cent == 0 && coins1Euro == 0
				&& coins2Euro == 0) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", "No money added.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		} else {
			Machine machine = (Machine) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get("loggedMachine");
			User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedUser");
			MoneyBusiness mb = new MoneyBusiness();
			AmountOfCoins amountOfCoinsAdded = new AmountOfCoins(coins5Cent, coins10Cent, coins20Cent, coins50Cent,
					coins1Euro, coins2Euro);
			double moneyInTheMachine = mb.moneyDeposit(machine, user, amountOfCoinsAdded);

			totalMoneyInTheMachine = moneyInTheMachine;
			totalMoney = 0.0;
			turnCoinsToZero(null);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
					"Money in the machine updated");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		}
	}
}
