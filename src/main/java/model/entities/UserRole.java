package model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * 
 * Tabela que guarda os tipo de role;
 *
 */

@Entity
public class UserRole {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userRoleId;
	
	private String description;

	public UserRole() {
		
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getUserRoleId() {
		return userRoleId;
	}
}
