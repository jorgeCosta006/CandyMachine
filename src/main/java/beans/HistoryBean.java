package beans;
/**
 * Bean responsavel pela view history: receber e alimentar a view com as 3 listas diferentes, uma de cliente com registo de compras, outra duas de admin, uma para moviemntos de dinheiro e outra de doces;
 */
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import model.business.HistoryBusiness;
import model.entities.CandyMovement;
import model.entities.Machine;
import model.entities.MoneyMovement;
import model.entities.User;

@ManagedBean(name = "historyBean", eager = true)
@RequestScoped
public class HistoryBean {

	private static final long serialVersionUID = 1L;
	
	private List<CandyMovement> candyMovement;
	private List<MoneyMovement> moneyMovement;
	private List<CandyMovement> candyMovementToClient;

	@PostConstruct
	public void list() {
		HistoryBusiness hb = new HistoryBusiness();
		User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedUser");
		Machine machine = (Machine) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("loggedMachine");
		if(user != null) {
			candyMovement = hb.returnCandyMovementList(machine.getMachineId());
			moneyMovement = hb.returnMoneyMovementList(machine.getMachineId());
			candyMovementToClient = hb.returnCandyMovementListToClient(user.getUserId());
		}
	}

	public List<CandyMovement> getCandyMovement() {
		return candyMovement;
	}

	public void setCandyMovement(List<CandyMovement> candyMovement) {
		this.candyMovement = candyMovement;
	}

	public List<MoneyMovement> getMoneyMovement() {
		return moneyMovement;
	}

	public void setMoneyMovement(List<MoneyMovement> moneyMovement) {
		this.moneyMovement = moneyMovement;
	}

	public List<CandyMovement> getCandyMovementToClient() {
		return candyMovementToClient;
	}

	public void setCandyMovementToClient(List<CandyMovement> candyMovementToClient) {
		this.candyMovementToClient = candyMovementToClient;
	}
}
