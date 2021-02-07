package model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
/**
 * 
 * Tabela que contem os users, nenhum user pode ser apagado, é feito um softDelete para possivel track de histórico;
 *
 */

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	private String name;
	private String email;
	private String password;
	private boolean isActive;

	@ManyToOne
	@JoinColumn(name="userRoleId")
	private UserRole userRole;

	public User() {	
	}
	
	public User(String name, String email, String password, Boolean isActive, UserRole userRole) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.isActive = isActive;
		this.userRole = userRole;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public int getUserId() {
		return userId;
	}
	
}
