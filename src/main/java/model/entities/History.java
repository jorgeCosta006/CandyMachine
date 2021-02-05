package model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class History {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int historyId;
	
	private String movimento;
	
	@OneToOne
	@JoinColumn(name="userId")
	private User user;
	
	@OneToOne
	@JoinColumn(name="purchaseId")
	private Purchase purchase;
	
	@OneToOne
	@JoinColumn(name="candyDepositId")
	private CandyDeposit candyDeposit;
	
	@OneToOne
	@JoinColumn(name="moneyDepositId")
	private MoneyDeposit moneyDeposit;
	
	public History() {
		
	}

	public History(String movimento, User user, Purchase purchase, CandyDeposit candyDeposit, MoneyDeposit moneyDeposit) {
		this.user = user;
		this.movimento = movimento;
		this.purchase = purchase;
		this.candyDeposit = candyDeposit;
		this.moneyDeposit = moneyDeposit;
	}

	public String getMovimento() {
		return movimento;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public CandyDeposit getCandyDeposit() {
		return candyDeposit;
	}

	public void setChoclateDeposit(CandyDeposit candyDeposit) {
		this.candyDeposit = candyDeposit;
	}

	public MoneyDeposit getMoneyDeposit() {
		return moneyDeposit;
	}

	public void setMoneyDeposit(MoneyDeposit moneyDeposit) {
		this.moneyDeposit = moneyDeposit;
	}

	public void setMovimento(String movimento) {
		this.movimento = movimento;
	}
}
