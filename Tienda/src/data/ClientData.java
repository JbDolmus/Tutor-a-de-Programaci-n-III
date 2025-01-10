package data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import domain.Cliente;

public class ClientData {
	
	private static String fileName = "src/database/clients.json";
	private static JsonUtils<Cliente> jsonUtils = new JsonUtils<>(fileName);
	
	public static ArrayList<Cliente> getClients(){
		ArrayList<Cliente> listClients = new ArrayList<>();
		try {
			Map<String, Cliente> clients = jsonUtils.getElements(Cliente.class);
			listClients.addAll(clients.values());
		} catch( IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return listClients;
	}
	
	public static void createClient(Cliente client) {
		try {
			Map<String, Cliente> clients = jsonUtils.getElements(Cliente.class);
			if(clients.containsKey(client.getIdentification())) {
				System.out.println("Ya existe un cliente con esa identificación");
				return;
			}
			jsonUtils.save(client, client.getIdentification());
			System.out.println("Cliente registrado exitosamente!");
			
		} catch ( IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateClientById(Cliente client, String id) {
		try {
			jsonUtils.updateById(client, client.getIdentification());
			System.out.println("Cliente actualizado exitosamente!");
			
		} catch ( IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteClientById(String id) {
		try {
			jsonUtils.deleteById(id, Cliente.class);
			System.out.println("Cliente eliminado exitosamente!");
			
		} catch ( IOException e) {
			e.printStackTrace();
		}
	}
}
