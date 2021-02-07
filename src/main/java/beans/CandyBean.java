package beans;
/**
 * Bean responável por mudanças em doces (exepto na compra que é feito no MachineBean);
 */
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
import model.entities.Machine;
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

	/**
	 * Metodo criar ou altera um doce já existente. Se não receber registos dá uma mensagem de erro, se criar dá uma mensagem de sucesso e actualiza a pagina;
	 */
	public String createOrUpdateCandy() {
		if ((candy == null && name == "") || price == 0 || quantity == 0) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message", "Must fill all data");
			PrimeFaces.current().dialog().showMessageDynamic(message);

			return "Erro";
		}

		MainPageBusiness mpb = new MainPageBusiness();
		User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedUser");
		Machine machine = (Machine) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("loggedMachine");
		boolean createdOrUpdated = mpb.createOrUpdateCandy(user, candy, machine, name, price, quantity);
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

	/**
	 * Metodo aplicado com intuito de trazer a modal de edição com registo do candy a ser alterado; (sem sucesso)
	 */
	public void updateCandyBean(Candy candy) {
		this.candy = candy;
		construct();
		PrimeFaces.current().executeScript("PF('crUser').show()");
	}

	public void deleteCandy() {

		try {
			User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedUser");
			Machine machine = (Machine) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get("loggedMachine");
			MainPageBusiness mpb = new MainPageBusiness();
			mpb.removeCandy(candy, user, machine);

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
					"Deleted! List of candies updated.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
			FacesContext.getCurrentInstance().getExternalContext().redirect("mainPage.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Para apagar os dados de um candy em registo no bean;
	 */
	public void eraseData() {
		name = "";
		price = 0.0;
		quantity = 0;
	}
}
