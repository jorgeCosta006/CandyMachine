package model.interfaces;

import java.util.List;

import model.entities.User;

public interface IUserDao {

	public void createOrUpdateUser(User user);
	
	public User userByEmail(String email);
	
	public User userById(int userId);
	
	public List<User> listOfUsers();
	
	public User getUserByEmailAndPassword(String email, String password);
}
