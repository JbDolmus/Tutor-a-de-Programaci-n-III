package business;

import data.BankData;
import domain.Bank;

public class Main {

	public static void main(String[] args) {
		//Bank b1 = new Bank("BN", "BN","bn@bn.com",3212000);
		//BankData.saveBank(b1);
		
		Bank updatedBank = new Bank("BN - Update", "RF-update","bcrrf.update@bcr.com",9212000);
		// Supongamos que queremos actualizar el banco con el nombre "Banco Viejo".
		BankData.updateBankByName(updatedBank, "BN");

		// Supongamos que queremos eliminar el banco con el nombre "Banco Viejo".
		//BankData.deleteBankByName("BN");


		BankData.print();

	}

}
