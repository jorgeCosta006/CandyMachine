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
public class Purchase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int purchaseId;
	
	@OneToOne
	@JoinColumn(name="userId", nullable = false)
	private User user;
	
	@OneToOne
	@JoinColumn(name="candyId", nullable = false)
	private Candy candy;
	
	@ManyToOne
	@JoinColumn(name="machineId")
	private Machine machine;
	
	private int quantity;
	private double totalPrice;
	private Date dateTime;
	
	public Purchase() {
		
	}

	public Purchase(User user, Candy candy, int quantity, double totalPrice, Date dateTime, Machine machine) {
		this.user = user;
		this.candy = candy;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.dateTime = dateTime;
		this.machine = machine;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Candy getCandy() {
		return candy;
	}

	public void setCandy(Candy candy) {
		this.candy = candy;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public int getPurchaseId() {
		return purchaseId;
	}
}
