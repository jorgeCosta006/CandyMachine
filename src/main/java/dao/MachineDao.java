package dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.entities.Machine;
import model.interfaces.IMachineDao;
import utilities.ConEntityManager;

public class MachineDao implements IMachineDao {
	static EntityManager em = ConEntityManager.getInstance();

	public void createOrUpadteMachine(Machine machine) {
		em.getTransaction().begin();
		em.persist(machine);
		em.getTransaction().commit();
	}

	public Machine machineById(int machineId) {
		Query q = em.createQuery("select p from Machine p where machineId =" + machineId + "");
		boolean machineNotExists = q.getResultList().isEmpty();
		if (machineNotExists)
			return null;

		Machine machine = (Machine) q.getSingleResult();
		return machine;
	}
	
	public Machine machineByName(String name) {
		Query q = em.createQuery("select p from Machine p where name = '" + name + "'");
		boolean machineNotExists = q.getResultList().isEmpty();
		if (machineNotExists)
			return null;

		Machine machine = (Machine) q.getSingleResult();
		return machine;
	}
}
