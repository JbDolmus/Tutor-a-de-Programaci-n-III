package business;

import java.time.LocalDate;
import java.util.LinkedList;

import data.ClientData;
import domain.Client;

public class Main {
	public static void main(String args[]) {
		
		LinkedList<Client> list = ClientData.getAll();
		for(Client c : list) {
			System.out.println(c.getName());
		}
		
		LocalDate birthdate = LocalDate.now();
		Client cl = new Client("Adan", "adan@gmail.com",
				"Rio Frio", 1234,10.222,10.222, birthdate);
		//ClientData.deleteClient(3);
		
	}

}
