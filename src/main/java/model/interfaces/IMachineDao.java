package model.interfaces;

import model.entities.Machine;

public interface IMachineDao {
	public void createOrUpadteMachine(Machine machine);

	public Machine machineById(int machineId);
	
	public Machine machineByName(String name);
}
