package model.interfaces;

import java.util.List;
import model.entities.Candy;

public interface ICandyDao {

	public void createOrUpdateCandy(Candy candy);
	
	public Candy findCandyById(int candyId);
	
	public List<Candy> listOfCandies();
}
