package model.business;

import Enums.EnumRoles;
import dao.MachineDao;
import dao.UserDao;
import model.entities.Machine;
import model.entities.User;
import model.interfaces.IMachineDao;
import model.interfaces.IUserDao;

public class AccountBusiness {

	public void addNewUser(String name, String email, String password, String role) {
		IUserDao ud = new UserDao();

		User user = new User(name, email, password, true, EnumRoles.valueOf(role));
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

	public User findUserById(int userId) {
		IUserDao ud = new UserDao();

		User user = ud.userById(userId);
		if (user == null)
			return null;

		return user;
	}

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

	public Machine findMachineById(int machineId) {
		IMachineDao md = new MachineDao();

		Machine machine = md.machineById(machineId);
		if (machine == null)
			return null;

		return machine;
	}

}
