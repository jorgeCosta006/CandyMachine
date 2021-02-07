package model.interfaces;

import java.util.List;

import model.entities.UserRole;

public interface IUserRoleDao {
	
	public UserRole findUserRoleByDescription(String name);
	
	public List<UserRole> listOfRoles();
}