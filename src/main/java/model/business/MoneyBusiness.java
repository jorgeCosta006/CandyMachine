package model.business;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

import dao.MachineDao;
import dao.MoneyMovementDao;
import model.entities.Machine;
import model.entities.MoneyMovement;
import model.entities.User;
import model.interfaces.IMachineDao;
import model.interfaces.IMoneyMovementDao;
import utilities.AmountOfCoins;

public class MoneyBusiness {

	public double moneyDeposit(Machine machine, User user, AmountOfCoins amountOfCoinsAdded) {

		IMachineDao md = new MachineDao();

		int coins5Cent = machine.getCoins5Cent() + amountOfCoinsAdded.getCoins5Cent();
		int coins10Cent = machine.getCoins10Cent() + amountOfCoinsAdded.getCoins10Cent();
		int coins20Cent = machine.getCoins20Cent() + amountOfCoinsAdded.getCoins20Cent();
		int coins50Cent = machine.getCoins50Cent() + amountOfCoinsAdded.getCoins50Cent();
		int coins1Euro = machine.getCoins1Euro() + amountOfCoinsAdded.getCoins1Euro();
		int coins2Euro = machine.getCoins2Euro() + amountOfCoinsAdded.getCoins2Euro();
		AmountOfCoins newAmountOfCoins = new AmountOfCoins(coins5Cent, coins10Cent, coins20Cent, coins50Cent,
				coins1Euro, coins2Euro);
		double newTotalAmountInTheMachine = calculateTotal(newAmountOfCoins);

		machine.setCoins5Cent(coins5Cent);
		machine.setCoins10Cent(coins10Cent);
		machine.setCoins20Cent(coins20Cent);
		machine.setCoins50Cent(coins50Cent);
		machine.setCoins1Euro(coins1Euro);
		machine.setCoins2Euro(coins2Euro);
		machine.setTotalCash(newTotalAmountInTheMachine);
		md.createOrUpadteMachine(machine);

		createMoneyMovement("In", amountOfCoinsAdded, user, machine);

		return newTotalAmountInTheMachine;
	}

	public void createMoneyMovement(String action, AmountOfCoins amountOfCoins, User user, Machine machine) {
		IMoneyMovementDao mmd = new MoneyMovementDao();

		double totalAmount = calculateTotal(amountOfCoins);
		MoneyMovement moneyMovement = new MoneyMovement(action, totalAmount, amountOfCoins.getCoins5Cent(),
				amountOfCoins.getCoins10Cent(), amountOfCoins.getCoins20Cent(), amountOfCoins.getCoins50Cent(),
				amountOfCoins.getCoins1Euro(), amountOfCoins.getCoins2Euro(), new Date(), user, machine);

		mmd.createOrUpdateMoneyMovent(moneyMovement);
	}

	public static double calculateTotal(AmountOfCoins amountOfCoins) {
		NumberFormat nf_in = NumberFormat.getNumberInstance(Locale.US);
		nf_in.setMaximumFractionDigits(2);

		double totalAmount = (0.05 * amountOfCoins.getCoins5Cent()) + (0.10 * amountOfCoins.getCoins10Cent())
				+ (0.20 * amountOfCoins.getCoins20Cent()) + (0.50 * amountOfCoins.getCoins50Cent())
				+ (1 * amountOfCoins.getCoins1Euro()) + (2 * amountOfCoins.getCoins2Euro());

		totalAmount = Double.valueOf(nf_in.format(totalAmount));

		return totalAmount;
	}

	public AmountOfCoins giveChange(Machine machine, AmountOfCoins AmountOfCoinsAdded, double changeValue) {
		NumberFormat nf_in = NumberFormat.getNumberInstance(Locale.US);
		nf_in.setMaximumFractionDigits(2);

		IMachineDao md = new MachineDao();

		AmountOfCoins aocToGive = new AmountOfCoins();

		while (changeValue >= 2.00) {
			int coins = machine.getCoins2Euro() + AmountOfCoinsAdded.getCoins2Euro();
			if (coins != 0) {
				changeValue -= 2;
				machine.setCoins2Euro(coins - 1);
				aocToGive.setCoins2Euro(aocToGive.getCoins2Euro() + 1);
			} else {
				break;
			}
		}

		while (changeValue >= 1.00) {
			int coins = machine.getCoins1Euro() + AmountOfCoinsAdded.getCoins1Euro();
			if (coins != 0) {
				changeValue -= 1;
				machine.setCoins1Euro(coins - 1);
				aocToGive.setCoins1Euro(aocToGive.getCoins1Euro() + 1);
			} else {
				break;
			}
		}

		while (changeValue >= 0.50) {
			int coins = machine.getCoins50Cent() + AmountOfCoinsAdded.getCoins50Cent();
			if (coins != 0) {
				changeValue = Double.valueOf(nf_in.format(changeValue - 0.50));
				machine.setCoins50Cent(coins - 1);
				aocToGive.setCoins50Cent(aocToGive.getCoins50Cent() + 1);
			} else {
				break;
			}
		}

		while (changeValue >= 0.20) {
			int coins = machine.getCoins20Cent() + AmountOfCoinsAdded.getCoins20Cent();
			if (coins != 0) {
				changeValue = Double.valueOf(nf_in.format(changeValue - 0.20));
				machine.setCoins20Cent(coins - 1);
				aocToGive.setCoins20Cent(aocToGive.getCoins20Cent() + 1);
			} else {
				break;
			}
		}

		while (changeValue >= 0.10) {
			int coins = machine.getCoins10Cent() + AmountOfCoinsAdded.getCoins10Cent();
			if (coins != 0) {
				changeValue = Double.valueOf(nf_in.format(changeValue - 0.10));
				machine.setCoins10Cent(coins - 1);
				aocToGive.setCoins10Cent(aocToGive.getCoins10Cent() + 1);
			} else {
				break;
			}
		}

		while (changeValue >= 0.05) {
			int coins = machine.getCoins5Cent() + AmountOfCoinsAdded.getCoins5Cent();
			if (coins != 0) {
				changeValue = Double.valueOf(nf_in.format(changeValue - 0.05));
				machine.setCoins5Cent(coins - 1);
				aocToGive.setCoins5Cent(aocToGive.getCoins5Cent() + 1);
			} else {
				break;
			}
		}

		if (changeValue == 0) {
			AmountOfCoins sumAmountOfCoins = new AmountOfCoins(machine.getCoins5Cent(), machine.getCoins10Cent(),
					machine.getCoins20Cent(), machine.getCoins50Cent(), machine.getCoins1Euro(),
					machine.getCoins2Euro());
			double totalAmount = calculateTotal(sumAmountOfCoins);
			machine.setTotalCash(totalAmount);
			md.createOrUpadteMachine(machine);
			return aocToGive;
		}

		return null;

	}
}
