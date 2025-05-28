import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BudgetManager manager = new BudgetManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Dodaj transakcję");
            System.out.println("2. Pokaż wszystkie transakcje");
            System.out.println("3. Pokaż saldo");
            System.out.println("4. Zapisz dane do pliku");
            System.out.println("5. Wczytaj dane z pliku");
            System.out.println("6. Filtruj po kategorii");
            System.out.println("7. Filtruj po miesiącu i roku");
            System.out.println("8. Filtruj po typie transakcji");
            System.out.println("9. Usuń transakcję");
            System.out.println("10. Edytuj transakcję");
            System.out.println("0. Wyjście");
            System.out.print("Wybierz opcję: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    try {
                        System.out.print("Nazwa: ");
                        String title = scanner.nextLine();

                        System.out.print("Kwota: ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();

                        System.out.print("Kategoria: ");
                        String category = scanner.nextLine();

                        System.out.print("Typ (INCOME/EXPENSE): ");
                        String typeInput = scanner.nextLine().toUpperCase();
                        Transaction.Type type = Transaction.Type.valueOf(typeInput);

                        Transaction transaction = new RecurringTransaction(title, amount, category, LocalDate.now(), type, 30);
                        manager.addTransaction(transaction);
                        System.out.println("Dodano transakcję.");
                        System.out.println("Łączna liczba transakcji: " + BudgetManager.getTransactionCount());

                    } catch (InvalidTransactionException e) {
                        System.out.println("Błąd: " + e.getMessage());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Niepoprawny typ transakcji.");
                    }
                }
                case 2 -> manager.showAllTransactions();
                case 3 -> System.out.println("Saldo: " + manager.getBalance() + " zł");
                case 4 -> {
                    System.out.print("Podaj nazwę pliku do zapisu (np. dane.csv): ");
                    String filename = scanner.nextLine();
                    manager.saveToFile(filename);
                }
                case 5 -> {
                    System.out.print("Podaj nazwę pliku do odczytu (np. dane.csv): ");
                    String filename = scanner.nextLine();
                    manager.loadFromFile(filename);
                }
                case 6 -> {
                    System.out.print("Podaj kategorię: ");
                    String cat = scanner.nextLine();
                    manager.filterByCategory(cat);
                }
                case 7 -> {
                    System.out.print("Podaj miesiąc (1-12): ");
                    int m = scanner.nextInt();
                    System.out.print("Podaj rok (np. 2025): ");
                    int y = scanner.nextInt();
                    scanner.nextLine(); // skip
                    manager.filterByMonthYear(m, y);
                }
                case 8 -> {
                    System.out.print("Typ (INCOME/EXPENSE): ");
                    String t = scanner.nextLine().toUpperCase();
                    Transaction.Type type = Transaction.Type.valueOf(t);
                    manager.filterByType(type);
                }
                case 9 -> {
                    manager.showAllTransactions();
                    System.out.print("Podaj numer transakcji do usunięcia: ");
                    int index = scanner.nextInt();
                    scanner.nextLine();
                    manager.deleteTransaction(index);
                }
                case 10 -> {
                    manager.showAllTransactions();
                    System.out.print("Podaj numer transakcji do edycji: ");
                    int index = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        System.out.print("Nowa nazwa: ");
                        String title = scanner.nextLine();
                        System.out.print("Nowa kwota: ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Nowa kategoria: ");
                        String category = scanner.nextLine();
                        System.out.print("Typ (INCOME/EXPENSE): ");
                        String typeStr = scanner.nextLine().toUpperCase();
                        Transaction.Type type = Transaction.Type.valueOf(typeStr);
                        Transaction updated = new Transaction(title, amount, category, LocalDate.now(), type);
                        manager.updateTransaction(index, updated);
                    } catch (InvalidTransactionException e) {
                        System.out.println("Błąd: " + e.getMessage());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Niepoprawny typ transakcji.");
                    }
                }

                case 0 -> {
                    System.out.println("Do widzenia!");
                    return;
                }
                default -> System.out.println("Niepoprawny wybór.");
            }
        }
    }
}