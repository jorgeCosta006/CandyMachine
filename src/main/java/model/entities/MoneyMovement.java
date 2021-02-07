package model.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MoneyMovement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int moneyMovementId;

	private String action;
	private double totalValue;
	private int coins5Cent;
	private int coins10Cent;
	private int coins20Cent;
	private int coins50Cent;
	private int coins1Euro;
	private int coins2Euro;
	private Date date;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	@ManyToOne
	@JoinColumn(name = "machineId")
	private Machine machine;

	public MoneyMovement() {

	}

	public MoneyMovement(String action, double totalValue, int coins5Cent, int coins10Cent, int coins20Cent,
			int coins50Cent, int coins1Euro, int coins2Euro, Date date, User user, Machine machine) {
		this.action = action;
		this.totalValue = totalValue;
		this.coins5Cent = coins5Cent;
		this.coins10Cent = coins10Cent;
		this.coins20Cent = coins20Cent;
		this.coins50Cent = coins50Cent;
		this.coins1Euro = coins1Euro;
		this.coins2Euro = coins2Euro;
		this.date = date;
		this.user = user;
		this.machine = machine;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}

	public int getCoins5Cent() {
		return coins5Cent;
	}

	public void setCoins5Cent(int coins5Cent) {
		this.coins5Cent = coins5Cent;
	}

	public int getCoins10Cent() {
		return coins10Cent;
	}

	public void setCoins10Cent(int coins10Cent) {
		this.coins10Cent = coins10Cent;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public int getMoneyMovementId() {
		return moneyMovementId;
	}

}
