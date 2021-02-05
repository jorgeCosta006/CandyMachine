package model.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class MoneyDeposit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int moneyDepositId;
	
	private double totalValue;
	private Date date;
	
	@OneToOne
	@JoinColumn(name="userId", nullable = false)
	private User user;
	
	@OneToOne
	@JoinColumn(name="amountOfCoinsId", nullable = false)
	private AmountOfCoins amountOfCoins;
	
	@ManyToOne
	@JoinColumn(name="machineId")
	private Machine machine;
	
	public MoneyDeposit() {
		
	}

	public MoneyDeposit(double totalValue, Date date, User user, AmountOfCoins amountOfCoins, Machine machine) {
		this.totalValue = totalValue;
		this.date = date;
		this.user = user;
		this.amountOfCoins = amountOfCoins;
		this.machine = machine;
	}

	public int getMoneyDepositId() {
		return moneyDepositId;
	}

	public void setMoneyDepositId(int moneyDepositId) {
		this.moneyDepositId = moneyDepositId;
	}

	public double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AmountOfCoins getAmountOfCoins() {
		return amountOfCoins;
	}
}
