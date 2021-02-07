package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.entities.User;
import model.interfaces.IUserDao;
import utilities.ConEntityManager;

public class UserDao implements IUserDao {

	// We don't remove users, we change isActive to false;
	public void createOrUpdateUser(User user) {
		EntityManager em = ConEntityManager.getInstance();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}

	public User userByEmail(String email) {
		EntityManager em = ConEntityManager.getInstance();
		Query userByEmail = em.createQuery("select p from User p where email = '" + email + "'");
		Boolean userNotExist = userByEmail.getResultList().isEmpty();
		if (userNotExist)
			return null;

		User user = (User) userByEmail.getSingleResult();
		return user;
	}

	public User userById(int userId) {
		EntityManager em = ConEntityManager.getInstance();
		Query userById = em.createQuery("select p from User p where userId = " + userId + "");
		Boolean userNotExist = userById.getResultList().isEmpty();
		if (userNotExist)
			return null;

		User user = (User) userById.getSingleResult();
		return user;
	}

	public List<User> listOfUsers() {
		EntityManager em = ConEntityManager.getInstance();
		Query query = em.createQuery("Select p from User p");
		Boolean userNotExist = query.getResultList().isEmpty();
		if (userNotExist)
			return null;

		List<User> list = query.getResultList();
		return list;
	}

//	public UserRole getUserRole(String role) {
//		Query q = em.createQuery("Select p from UserRole p where description = '" + role + "'");
//		if(q.equals(null))
//			return null;
//		
//		UserRole userRole = (UserRole) q.getSingleResult();
//		return userRole;
//	}
	
	public User getUserByEmailAndPassword(String email, String password) {
		EntityManager em = ConEntityManager.getInstance();
		Query query = em.createQuery("Select p from User p where email = '" + email + "' and password = '" + password + "'");
		Boolean userNotExist = query.getResultList().isEmpty();
		if(userNotExist)
			return null;
		
		User user = (User) query.getSingleResult();
		return user;
	}

}
