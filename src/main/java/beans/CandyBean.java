package beans;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import model.business.MainPageBusiness;
import model.entities.Candy;
import model.entities.User;

@ManagedBean(name = "candyBean", eager = true)
@ViewScoped
public class CandyBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Candy candy;
	private String name;
	private double price;
	private int quantity;

	@PostConstruct
	public void construct() {
		if (candy != null) {
			name = candy.getName();
			price = candy.getPrice();
			quantity = candy.getQuantity();
		}
	}

	public Candy getCandy() {
		return candy;
	}

	public void setCandy(Candy candy) {
		this.candy = candy;
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

	public String createOrUpdateCandy() {
		if (name == null || price == 0 || quantity == 0) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message", "Must fill all data");
			PrimeFaces.current().dialog().showMessageDynamic(message);

			return "Erro";
		}

		MainPageBusiness mpb = new MainPageBusiness();
		User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedUser");
		boolean createdOrUpdated = mpb.createOrUpdateCandy(user, candy, name, price, quantity);
		FacesMessage message = null;

		try {
			if (createdOrUpdated) {
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", "Done! List of candies updated.");
				FacesContext.getCurrentInstance().getExternalContext().redirect("mainPage.xhtml");
			} else {
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"There's already a candy with that name.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		eraseData();
		PrimeFaces.current().dialog().showMessageDynamic(message);
		return "";
	}

	public void candyToNull() {
		this.candy = null;
	}

	public void receiveCandyObject(Candy candy) {
		this.candy = candy;
	}

	public void updateCandyBean(Candy candy) {
		this.candy = candy;
		construct();
		PrimeFaces.current().executeScript("PF('updateCandy').show()");
	}

	public void deleteCandy() {
		MainPageBusiness mpb = new MainPageBusiness();
		mpb.removeCandy(candy);

		try {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
					"Deleted! List of candies updated.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
			FacesContext.getCurrentInstance().getExternalContext().redirect("mainPage.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void eraseData() {
		name = "";
		price = 0.0;
		quantity = 0;
	}
}
