package model.interfaces;

import java.util.List;
import model.entities.History;

public interface IHistoryDao {
	public void newHistory(History history);
	
	public List<History> getHistoryList(int userId);
	
	public List<History> getCandyDepositHistoryList(int userId);
	
	public List<History> getMoneyDepositHistoryList(int userId);
}
