package model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * 
 * Tabela que guarda registo doces, nenhum doce pode ser apagado, é feito um softDelete para possivel track de histórico;
 *
 */

@Entity
public class Candy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int candyId;
	
	private String name;
	
	private double price;
	private int quantity;
	//The application don't delete a candy, it turns to false (because the id it's a foreign key in other tables)
	private boolean isActive;
	
	public Candy() {
		
	}

	public Candy(String name, double price, int quantity, boolean isActive) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.isActive = isActive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getCandyId() {
		return candyId;
	}
}
