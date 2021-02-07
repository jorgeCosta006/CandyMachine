package model.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CandyMovement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int candyMovementId;
	
	private String action;
	private String candyName;
	private Double price;
	private Integer quantity;
	private Date date;
	
	@ManyToOne
	@JoinColumn(name="candyId")
	private Candy candy;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="machineId")
	private Machine machine;

	public CandyMovement() {
		
	}
	
	public CandyMovement(String action, String candyName, Double price, Integer quantity, Date date, Candy candy,
			User user, Machine machine) {
		this.action = action;
		this.candyName = candyName;
		this.price = price;
		this.quantity = quantity;
		this.date = date;
		this.candy = candy;
		this.user = user;
		this.machine = machine;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getCandyName() {
		return candyName;
	}

	public void setCandyName(String candyName) {
		this.candyName = candyName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Candy getCandy() {
		return candy;
	}

	public void setCandy(Candy candy) {
		this.candy = candy;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public int getCandyMovementId() {
		return candyMovementId;
	}
}
