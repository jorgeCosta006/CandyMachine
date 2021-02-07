package beans;
/**
 * Bean responsavel pela configurationPage: receber registo do user que está em sessão e realização de alterações;
 */
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import model.business.AccountBusiness;
import model.entities.User;

@ManagedBean(name = "configurationsBean", eager = true)
@ViewScoped
public class ConfigurationsBean {
	private static final long serialVersionUID = 1L;
	private String name;
	private String password;
	private String rpPassword;

	@PostConstruct
	public void construct() {
		User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedUser");
		if (user != null) {
			name = user.getName();
			password = user.getPassword();
			rpPassword = user.getPassword();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRpPassword() {
		return rpPassword;
	}

	public void setRpPassword(String rpPassword) {
		this.rpPassword = rpPassword;
	}

	public String changeUserData() {
		if (name == "" || password == "" || rpPassword == "") {

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message", "Must fill all data");
			PrimeFaces.current().dialog().showMessageDynamic(message);
			return "";
		}

		if (password.equals(rpPassword)) {
			AccountBusiness ab = new AccountBusiness();
			User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedUser");
			ab.changeDataUser(user, name, password);

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", "User information updated!");
			PrimeFaces.current().dialog().showMessageDynamic(message);
			return "";

		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message", "Passwords must match!");
			PrimeFaces.current().dialog().showMessageDynamic(message);
			return "";
		}
	}
}
