import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BudgetManager {
    private List<Transaction> transactions;

    public BudgetManager() {
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public double getBalance() {
        double balance = 0;
        for (Transaction t : transactions) {
            if (t.getType() == Transaction.Type.INCOME) {
                balance += t.getAmount();
            } else {
                balance -= t.getAmount();
            }
        }
        return balance;
    }

    public void showAllTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("Brak transakcji.");
        } else {
            for (Transaction t : transactions) {
                System.out.println(t);
            }
        }
    }


    public void saveToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Transaction t : transactions) {
                writer.write(t.getTitle() + "," +
                        t.getAmount() + "," +
                        t.getCategory() + "," +
                        t.getDate() + "," +
                        t.getType() + "\n");
            }
            System.out.println("Zapisano transakcje do pliku: " + filename);
        } catch (IOException e) {
            System.out.println("Błąd zapisu: " + e.getMessage());
        }
    }


    public void loadFromFile(String filename) {
        transactions.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String title = parts[0];
                double amount = Double.parseDouble(parts[1]);
                String category = parts[2];
                LocalDate date = LocalDate.parse(parts[3]);
                Transaction.Type type = Transaction.Type.valueOf(parts[4]);

                Transaction t = new Transaction(title, amount, category, date, type);
                transactions.add(t);
            }
            System.out.println("Wczytano transakcje z pliku: " + filename);
        } catch (IOException | InvalidTransactionException e) {
            System.out.println("Błąd odczytu: " + e.getMessage());
        }
    }
    public void deleteTransaction(int index) {
        if (index >= 0 && index < transactions.size()) {
            transactions.remove(index);
            System.out.println("Usunięto transakcję nr " + index);
        } else {
            System.out.println("Niepoprawny indeks.");
        }
    }
    public void updateTransaction(int index, Transaction updated) {
        if (index >= 0 && index < transactions.size()) {
            transactions.set(index, updated);
            System.out.println("Zaktualizowano transakcję nr " + index);
        } else {
            System.out.println("Niepoprawny indeks.");
        }
    }


    public void filterByCategory(String category) {
        boolean found = false;
        for (Transaction t : transactions) {
            if (t.getCategory().equalsIgnoreCase(category)) {
                System.out.println(t);
                found = true;
            }
        }
        if (!found) System.out.println("Brak transakcji w tej kategorii.");
    }

    public void filterByMonthYear(int month, int year) {
        boolean found = false;
        for (Transaction t : transactions) {
            if (t.getDate().getMonthValue() == month && t.getDate().getYear() == year) {
                System.out.println(t);
                found = true;
            }
        }
        if (!found) System.out.println("Brak transakcji w podanym miesiącu i roku.");
    }

    public void filterByType(Transaction.Type type) {
        boolean found = false;
        for (Transaction t : transactions) {
            if (t.getType() == type) {
                System.out.println(t);
                found = true;
            }
        }
        if (!found) System.out.println("Brak transakcji typu " + type);
    }
}