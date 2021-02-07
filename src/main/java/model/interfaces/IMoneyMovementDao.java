package model.interfaces;

import java.util.List;

import model.entities.MoneyMovement;

public interface IMoneyMovementDao {
	public void createOrUpdateMoneyMovent(MoneyMovement moneyMovement);
	
	public List<MoneyMovement> moneyMovementByMachineId(int machineId);
}
