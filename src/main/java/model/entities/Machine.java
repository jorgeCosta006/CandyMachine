package model.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Machine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int machineId;
	
	private String name;
	private double totalCash;
	
	@OneToOne
	@JoinColumn(name="amountOfCoinsId")
	private AmountOfCoins amountOfCoins;
	
	@OneToMany
	private List<Candy> candies;
	
	public Machine() {
		
	}

	public Machine(String name, AmountOfCoins amountOfCoins) {
		this.name = name;
		this.amountOfCoins = amountOfCoins;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTotalCash() {
		return totalCash;
	}

	public void setTotalCash(double totalCash) {
		this.totalCash = totalCash;
	}

	public AmountOfCoins getAmountOfCoins() {
		return amountOfCoins;
	}

	public void setAmountOfCoins(AmountOfCoins amountOfCoins) {
		this.amountOfCoins = amountOfCoins;
	}

	public List<Candy> getCandies() {
		return candies;
	}

	public void setCandies(List<Candy> candies) {
		this.candies = candies;
	}

	public int getMachineId() {
		return machineId;
	}
}
