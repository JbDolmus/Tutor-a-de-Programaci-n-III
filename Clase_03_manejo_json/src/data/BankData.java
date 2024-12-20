package data;

import java.io.IOException;
import java.util.Map;
import business.LogicBank;
import domain.Bank;

public class BankData {
    private static String fileName = "src/database/banks.json";
    private static JsonUtils<Bank> jsonUtils = new JsonUtils<>(fileName);

    public static void print() {
        try {
            Map<String, Bank> banks = jsonUtils.getElements(Bank.class);
            if (banks.isEmpty()) {
                System.err.println("Sin elementos para mostrar");
                return;
            }
            for (String name : banks.keySet()) {
                System.out.println(name + ": " + banks.get(name).getAddress());
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void saveBank(Bank bank) {
        try {
            Map<String, Bank> banks = jsonUtils.getElements(Bank.class);
            if (banks.containsKey(bank.getName())) {
                System.err.println("Ya existe un banco con el nombre " + bank.getName());
                return;
            }
            jsonUtils.save(bank, bank.getName());
            System.out.println("Banco guardado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateBankByName(Bank updatedBank, String name) {
        try {
            jsonUtils.updateByName(updatedBank, name);
            System.out.println("Banco actualizado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al actualizar el banco: " + e.getMessage());
        }
    }

    public static void deleteBankByName(String name) {
        try {
            jsonUtils.deleteByName(name, Bank.class);
            System.out.println("Banco eliminado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al eliminar el banco: " + e.getMessage());
        }
    }
}
