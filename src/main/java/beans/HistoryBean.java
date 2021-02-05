package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import model.business.HistoryBusiness;
import model.entities.History;
import model.entities.User;

@ManagedBean(name = "historyBean", eager = true)
@RequestScoped
public class HistoryBean {

	private static final long serialVersionUID = 1L;
	private List<History> history;
	private List<History> candyDepositHistory;
	private List<History> moneyDepositHistory;

	@PostConstruct
	public void list() {
		HistoryBusiness hb = new HistoryBusiness();
		User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedUser");
		if(user != null) {
			history = hb.returnHistoryList(user.getUserId());
			candyDepositHistory = hb.returnCandyDepositHistoryList(user.getUserId());
			moneyDepositHistory = hb.returnMoneyDepositHistoryList(user.getUserId());
		}
	}

	public List<History> getHistory() {
		return history;
	}

	public void setHistory(List<History> history) {
		this.history = history;
	}

	public List<History> getCandyDepositHistory() {
		return candyDepositHistory;
	}

	public void setCandyDepositHistory(List<History> candyDepositHistory) {
		this.candyDepositHistory = candyDepositHistory;
	}

	public List<History> getMoneyDepositHistory() {
		return moneyDepositHistory;
	}

	public void setMoneyDepositHistory(List<History> moneyDepositHistory) {
		this.moneyDepositHistory = moneyDepositHistory;
	}
}
