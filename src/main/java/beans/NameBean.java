package beans;

/**
 * Bean de request responsável para devolver o nome do user em sessão;
 */
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import model.entities.User;

@ManagedBean(name = "nameBean", eager = true)
@RequestScoped
public class NameBean {
	private String name;

	@PostConstruct
	public void construct() {
		User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedUser");
		if (user != null)
			setName(user.getName());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
