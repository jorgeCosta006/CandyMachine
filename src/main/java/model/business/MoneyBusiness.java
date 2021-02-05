package model.business;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

import dao.AmountOfCoinsDao;
import dao.HistoryDao;
import dao.MachineDao;
import dao.MoneyDepositDao;
import model.entities.AmountOfCoins;
import model.entities.History;
import model.entities.Machine;
import model.entities.MoneyDeposit;
import model.entities.User;
import model.interfaces.IAmountOfCoinsDao;
import model.interfaces.IHistoryDao;
import model.interfaces.IMachineDao;
import model.interfaces.IMoneyDepositDao;

public class MoneyBusiness {

	public double moneyDeposit(Machine machine, User user, int coins5Cent, int coins10Cent, int coins20Cent,
			int coins50Cent, int coins1Euro, int coins2Euro) {
		IAmountOfCoinsDao acd = new AmountOfCoinsDao();
		IMoneyDepositDao mdd = new MoneyDepositDao();
		IHistoryDao hd = new HistoryDao();

		AmountOfCoins amountOfCoins = new AmountOfCoins(coins5Cent, coins10Cent, coins20Cent, coins50Cent, coins1Euro,
				coins2Euro);
		acd.createOrUpdateAmountOfCoins(amountOfCoins);

		double totalAmount = (0.05 * coins5Cent) + (0.10 * coins10Cent) + (0.20 * coins20Cent) + (0.50 * coins50Cent)
				+ (1 * coins1Euro) + (2 * coins2Euro);

		double moneyInTheMachine = changeMoneyinMachine(machine, amountOfCoins);

		MoneyDeposit moneyDeposit = new MoneyDeposit(totalAmount, new Date(), user, amountOfCoins, machine);
		mdd.createMoneyDeposit(moneyDeposit);
		History history = new History("Money deposit:", user, null, null, moneyDeposit);
		hd.newHistory(history);

		System.out.println("Money deposit");
		return moneyInTheMachine;
	}

	public double changeMoneyinMachine(Machine machine, AmountOfCoins coinsToTheMachine) {
		IAmountOfCoinsDao acd = new AmountOfCoinsDao();
		IMachineDao md = new MachineDao();

		AmountOfCoins amountOfCoinsInMachine = machine.getAmountOfCoins();

		int coins5Cent = amountOfCoinsInMachine.getCoins5Cent() + coinsToTheMachine.getCoins5Cent();
		int coins10Cent = amountOfCoinsInMachine.getCoins10Cent() + coinsToTheMachine.getCoins10Cent();
		int coins20Cent = amountOfCoinsInMachine.getCoins20Cent() + coinsToTheMachine.getCoins20Cent();
		int coins50Cent = amountOfCoinsInMachine.getCoins50Cent() + coinsToTheMachine.getCoins50Cent();
		int coins1Euro = amountOfCoinsInMachine.getCoins1Euro() + coinsToTheMachine.getCoins1Euro();
		int coins2Euro = amountOfCoinsInMachine.getCoins2Euro() + coinsToTheMachine.getCoins2Euro();
		double totalAmount = (0.05 * coins5Cent) + (0.10 * coins10Cent) + (0.20 * coins20Cent) + (0.50 * coins50Cent)
				+ (1 * coins1Euro) + (2 * coins2Euro);

		amountOfCoinsInMachine.setCoins5Cent(coins5Cent);
		amountOfCoinsInMachine.setCoins10Cent(coins10Cent);
		amountOfCoinsInMachine.setCoins20Cent(coins20Cent);
		amountOfCoinsInMachine.setCoins50Cent(coins50Cent);
		amountOfCoinsInMachine.setCoins1Euro(coins1Euro);
		amountOfCoinsInMachine.setCoins2Euro(coins2Euro);
		acd.createOrUpdateAmountOfCoins(amountOfCoinsInMachine);

		machine.setTotalCash(totalAmount);
		md.createOrUpadteMachine(machine);
		
		return totalAmount;

	}

	public AmountOfCoins giveChange(Machine machine, double changeValue) {
		NumberFormat nf_in = NumberFormat.getNumberInstance(Locale.US);
		nf_in.setMaximumFractionDigits(2);
		
		IAmountOfCoinsDao acd = new AmountOfCoinsDao();
		IMachineDao md = new MachineDao();

		AmountOfCoins aocToGive = new AmountOfCoins();
		AmountOfCoins aoc = machine.getAmountOfCoins();

//		Double changeToGive = 0.0;

		while (changeValue >= 2.00) {
			int coins = aoc.getCoins2Euro();
			if (coins != 0) {
//				changeToGive += 2;
				changeValue -= 2;
				aoc.setCoins2Euro(coins - 1);
				aocToGive.setCoins2Euro(aocToGive.getCoins2Euro() + 1);
			} else {
				break;
			}
		}

		while (changeValue >= 1.00) {
			int coins = aoc.getCoins1Euro();
			if (coins != 0) {
//				changeToGive += 1;
				changeValue -= 1;
				aoc.setCoins1Euro(coins - 1);
				aocToGive.setCoins1Euro(aocToGive.getCoins1Euro() + 1);
			} else {
				break;
			}
		}

		while (changeValue >= 0.50) {
			int coins = aoc.getCoins50Cent();
			if (coins != 0) {
//				changeToGive = Double.valueOf(nf_in.format(changeToGive + 0.50));
				changeValue = Double.valueOf(nf_in.format(changeValue - 0.50));
				aoc.setCoins50Cent(coins - 1);
				aocToGive.setCoins50Cent(aocToGive.getCoins50Cent() + 1);
			} else {
				break;
			}
		}

		while (changeValue >= 0.20) {
			int coins = aoc.getCoins20Cent();
			if (coins != 0) {
//				changeToGive = Double.valueOf(nf_in.format(changeToGive + 0.20));
				changeValue = Double.valueOf(nf_in.format(changeValue - 0.20));
				aoc.setCoins20Cent(coins - 1);
				aocToGive.setCoins20Cent(aocToGive.getCoins20Cent() + 1);
			} else {
				break;
			}
		}

		while (changeValue >= 0.10) {
			int coins = aoc.getCoins10Cent();
			if (coins != 0) {
//				changeToGive += 0.10;
				changeValue = Double.valueOf(nf_in.format(changeValue - 0.10));
				aoc.setCoins10Cent(coins - 1);
				aocToGive.setCoins10Cent(aocToGive.getCoins10Cent() + 1);
			} else {
				break;
			}
		}

		while (changeValue >= 0.05) {
			int coins = aoc.getCoins5Cent();
			if (coins != 0) {
//				changeToGive += 0.05;
				changeValue = Double.valueOf(nf_in.format(changeValue - 0.05));
				aoc.setCoins5Cent(coins - 1);
				aocToGive.setCoins5Cent(aocToGive.getCoins5Cent() + 1);
			} else {
				break;
			}
		}

		if (changeValue == 0) {
			double totalAmount = (0.05 * aoc.getCoins5Cent()) + (0.10 * aoc.getCoins10Cent())
					+ (0.20 * aoc.getCoins20Cent()) + (0.50 * aoc.getCoins50Cent()) + (1 * aoc.getCoins1Euro())
					+ (2 * aoc.getCoins2Euro());
			machine.setTotalCash(totalAmount);
			md.createOrUpadteMachine(machine);
			acd.createOrUpdateAmountOfCoins(aoc);
			return aocToGive;
		}

		return null;

	}

	public AmountOfCoins createAmountOfCoins(int coins5Cent, int coins10Cent, int coins20Cent, int coins50Cent,
			int coins1Euro, int coins2Euro) {
		IAmountOfCoinsDao acd = new AmountOfCoinsDao();
		AmountOfCoins amountOfCoins = new AmountOfCoins(coins5Cent, coins10Cent, coins20Cent, coins50Cent, coins1Euro,
				coins2Euro);
		acd.createOrUpdateAmountOfCoins(amountOfCoins);
		return amountOfCoins;
	}

	public static double calculateTotalMoney(AmountOfCoins aoc) {
		double totalAmount = (0.05 * aoc.getCoins5Cent()) + (0.10 * aoc.getCoins10Cent())
				+ (0.20 * aoc.getCoins20Cent()) + (0.50 * aoc.getCoins50Cent()) + (1 * aoc.getCoins1Euro())
				+ (2 * aoc.getCoins2Euro());

		return totalAmount;
	}
}
