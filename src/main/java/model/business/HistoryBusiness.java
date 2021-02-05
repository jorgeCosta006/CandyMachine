package model.business;

import java.util.List;

import dao.HistoryDao;
import model.entities.History;
import model.interfaces.IHistoryDao;

public class HistoryBusiness {
	
	
	public List<History> returnHistoryList(int userId) {
		IHistoryDao hd = new HistoryDao();
		
		return hd.getHistoryList(userId);
	}
	
	public List<History> returnCandyDepositHistoryList(int userId) {
		IHistoryDao hd = new HistoryDao();
		
		return hd.getCandyDepositHistoryList(userId);
	}
	
	public List<History> returnMoneyDepositHistoryList(int userId) {
		IHistoryDao hd = new HistoryDao();
		
		return hd.getMoneyDepositHistoryList(userId);
	}
}
