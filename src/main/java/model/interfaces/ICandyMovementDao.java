package model.interfaces;

import java.util.List;

import model.entities.CandyMovement;

public interface ICandyMovementDao {
	
	public void createOrUpdateCandyMovement(CandyMovement candyMovement);
	
	public List<CandyMovement> candyMovementByMachineId(int machineId);
	
	public List<CandyMovement> candyMovementByUserId(int userId);
}
