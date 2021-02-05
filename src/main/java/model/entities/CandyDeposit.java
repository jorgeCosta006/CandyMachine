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
public class CandyDeposit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int candyDepositId;
	
	@OneToOne
	@JoinColumn(name="candyId", nullable = false)
	private Candy candy;
	
	@OneToOne
	@JoinColumn(name="userId", nullable = false)
	private User userId;
	
	@ManyToOne
	@JoinColumn(name="machineId")
	private Machine machine;
	
	private int quantity;
	private Date date;
	
	public CandyDeposit() {
		
	}
	
	public CandyDeposit(Candy candy, User userId, int quantity, Date date, Machine machine) {
		this.candy = candy;
		this.userId = userId;
		this.quantity = quantity;
		this.date = date;
		this.machine = machine;
	}

	public Candy getCandy() {
		return candy;
	}

	public void setCandy(Candy candy) {
		this.candy = candy;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCandyDepositId() {
		return candyDepositId;
	}	
}
