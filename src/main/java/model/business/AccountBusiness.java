package model.business;

import java.util.List;

import dao.MachineDao;
import dao.UserDao;
import dao.UserRoleDao;
import model.entities.Machine;
import model.entities.User;
import model.entities.UserRole;
import model.interfaces.IMachineDao;
import model.interfaces.IUserDao;
import model.interfaces.IUserRoleDao;

public class AccountBusiness {

	public void addNewUser(String name, String email, String password, String userRoleDescription) {
		IUserDao ud = new UserDao();
		IUserRoleDao urd = new UserRoleDao();
		
		UserRole userRole = urd.findUserRoleByDescription(userRoleDescription);

		User user = new User(name, email, password, true, userRole);
		ud.createOrUpdateUser(user);
	}

	public void changeDataUser(User user, String name, String password) {
		user.setName(name);
		user.setPassword(password);

		UserDao userDao = new UserDao();
		userDao.createOrUpdateUser(user);
	}

	public User findUserByEmailAndPassword(String email, String password) {
		IUserDao ud = new UserDao();

		User user = ud.getUserByEmailAndPassword(email, password);
		if (user == null) {
			return null;
		} else {
			return user;
		}
	}

//	public User findUserById(int userId) {
//		IUserDao ud = new UserDao();
//
//		User user = ud.userById(userId);
//		if (user == null)
//			return null;
//
//		return user;
//	}

	public User findUserByEmail(String email) {
		IUserDao ud = new UserDao();

		User user = ud.userByEmail(email);
		if (user == null)
			return null;

		return user;
	}

	public Machine findMachineByName(String name) {
		IMachineDao md = new MachineDao();

		Machine machine = md.machineByName(name);
		if (machine == null)
			return null;

		return machine;
	}
	
	public List<UserRole> returnUserRoles() {
		IUserRoleDao urd = new UserRoleDao();
		
		List<UserRole> list = urd.listOfRoles();
		
		if (list == null)
			return null;

		return list;
	}

//	public Machine findMachineById(int machineId) {
//		IMachineDao md = new MachineDao();
//
//		Machine machine = md.machineById(machineId);
//		if (machine == null)
//			return null;
//
//		return machine;
//	}

}
