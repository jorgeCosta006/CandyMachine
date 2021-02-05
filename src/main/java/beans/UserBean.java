package beans;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import model.business.AccountBusiness;
import model.entities.Machine;
import model.entities.User;

@ManagedBean(name = "userBean", eager = true)
@SessionScoped
public class UserBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String email;
	private String password;
	private String role;
	private boolean errorLogin = false;

	/* CREATE USER */
	private String name;
	private String crEmail;
	private String crPassword;
	private String repeatCrPassword;
	private String crRole;

	private User user;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isErrorLogin() {
		return errorLogin;
	}

	public void setErrorLogin(boolean errorLogin) {
		this.errorLogin = errorLogin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCrEmail() {
		return crEmail;
	}

	public void setCrEmail(String crEmail) {
		this.crEmail = crEmail;
	}

	public String getCrPassword() {
		return crPassword;
	}

	public void setCrPassword(String crPassword) {
		this.crPassword = crPassword;
	}

	public String getRepeatCrPassword() {
		return repeatCrPassword;
	}

	public void setRepeatCrPassword(String repeatCrPassword) {
		this.repeatCrPassword = repeatCrPassword;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCrRole() {
		return crRole;
	}

	public void setCrRole(String crRole) {
		this.crRole = crRole;
	}

	public void processLogin() {
		AccountBusiness ab = new AccountBusiness();
		User user = ab.findUserByEmailAndPassword(email, password);

		try {
			if (user == null) {
				errorLogin = true;
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
						"Wrong email or password");
				PrimeFaces.current().dialog().showMessageDynamic(message);
			} else {
				Machine machine = ab.findMachineByName("ChoclateFactory");
				errorLogin = false;
				this.user = user;
				role = user.getUserRole().name();
				name = user.getName();
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedUser", user);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedMachine", machine);
				FacesContext.getCurrentInstance().getExternalContext().redirect("mainPage.xhtml");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String createUser() {
		AccountBusiness account = new AccountBusiness();
		User user = account.findUserByEmail(crEmail);

		if (this.name == "" || this.crEmail == "" || this.crPassword == "" || this.role == null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message", "Must fill all data.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
			return "Must fill all data";
		} else if (!this.crPassword.equals(repeatCrPassword)) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message", "Passwords must match.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
			return "Passwords must match.";
		}

		if (user == null) {
			try {
				account.addNewUser(name, crEmail, crPassword, role);
				FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", "User created.");
				PrimeFaces.current().dialog().showMessageDynamic(message);
				return "Created";
			} catch (IOException e) {
				e.printStackTrace();
				return "Error";
			}
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message", "Email already exists.");
			PrimeFaces.current().dialog().showMessageDynamic(message);
			return "Email already exists";
		}
	}

	public void processLogout() {
		try {
			this.user = null;
			this.name = "";
			this.email = "";
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedUser", null);
			FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void changePage(String page) {
		try {

			switch (page) {
			case "mainPage":
				FacesContext.getCurrentInstance().getExternalContext().redirect("mainPage.xhtml");
				break;
			case "historyPage":
				FacesContext.getCurrentInstance().getExternalContext().redirect("historyPage.xhtml");
				break;
			case "moneyDepositPage":
				FacesContext.getCurrentInstance().getExternalContext().redirect("moneyDepositPage.xhtml");
				break;
			case "configurationsPage":
				FacesContext.getCurrentInstance().getExternalContext().redirect("configurationsPage.xhtml");
				break;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}