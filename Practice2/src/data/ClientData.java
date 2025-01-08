package data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import domain.Cliente;

public class ClientData {
	
	private static String fileName = "src/database/clients.json";
    private static JsonUtils<Cliente> jsonUtils = new JsonUtils<>(fileName);
    
    public static ArrayList<Cliente> getClients() {
        ArrayList<Cliente> clientList = new ArrayList<>();
        try {
            Map<String, Cliente> clientes = jsonUtils.getElements(Cliente.class);
            clientList.addAll(clientes.values()); // Agrega todos los clientes a la lista
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return clientList;
    }


    public static void saveClient(Cliente client) {
        try {
            Map<String, Cliente> banks = jsonUtils.getElements(Cliente.class);
            if (banks.containsKey(client.getCedula())) {
                System.err.println("Ya existe un cliente con la cedula " + client.getCedula());
                return;
            }
            jsonUtils.save(client, client.getCedula());
            System.out.println("Cliente registrado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateClientById(Cliente updatedClient, String id) {

        try {
            jsonUtils.updateById(updatedClient, id);
            System.out.println("Cliente actualizado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al actualizar el cliente: " + e.getMessage());
        }
    }

    public static void deleteClientById(String id) {
        try {
            jsonUtils.deleteById(id, Cliente.class);
            System.out.println("Cliente eliminado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al eliminar el cliente: " + e.getMessage());
        }
    }
}
