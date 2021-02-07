package model.business;

import java.util.List;

import dao.CandyMovementDao;
import dao.MoneyMovementDao;
import model.entities.CandyMovement;
import model.entities.MoneyMovement;
import model.interfaces.ICandyMovementDao;
import model.interfaces.IMoneyMovementDao;

public class HistoryBusiness {
	
	public List<MoneyMovement> returnMoneyMovementList(int machineId){
		IMoneyMovementDao mmd = new MoneyMovementDao();
		
		return mmd.moneyMovementByMachineId(machineId);
	}
	
	public List<CandyMovement> returnCandyMovementList(int machineId){
		ICandyMovementDao cmd = new CandyMovementDao();
		
		return cmd.candyMovementByMachineId(machineId);
	}
	
	public List<CandyMovement> returnCandyMovementListToClient(int userId){
		ICandyMovementDao cmd = new CandyMovementDao();
		
		return cmd.candyMovementByUserId(userId);
	}
}