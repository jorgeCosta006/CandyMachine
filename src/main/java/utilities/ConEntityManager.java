package utilities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ConEntityManager {
    private static final String PERSISTENCE_UNIT_NAME = "candyMachineDB";
    private static EntityManagerFactory factory;
	private	static ConEntityManager entityManager;
	private	static EntityManager em;
	
	
	public static synchronized EntityManager getInstance() {
		if (entityManager == null){
			entityManager = new ConEntityManager();
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	        em = factory.createEntityManager();
	        return em;
		}
		return em;
	}
	
	public Object returnTableDataWithFilter(String tableName, String filterName, String value) {

		Query q = em.createQuery("select p from " + tableName + " p where "+ filterName+ " = '" + value + "'");
		return q;
	}

}
