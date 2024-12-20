package business;

import java.util.ArrayList;

import domain.Bank;

public class LogicBank {
	public static boolean issetBank(ArrayList<Bank> list, Bank b) {
		for(Bank t: list) {
			if(t.getName().equals(b.getName())) {
				return true;
			}
		}
		return false;
	}
}
