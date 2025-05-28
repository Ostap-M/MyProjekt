import java.time.LocalDate;

public class Transaction {
    public enum Type { INCOME, EXPENSE }

    private String title;
    private double amount;
    private String category;
    private LocalDate date;
    private Type type;

    // Конструктор з перевірками — кидає власний виняток
    public Transaction(String title, double amount, String category, LocalDate date, Type type) throws InvalidTransactionException {
        if (title == null || title.isBlank()) {
            throw new InvalidTransactionException("Nazwa nie może być pusta.");
        }
        if (amount <= 0) {
            throw new InvalidTransactionException("Kwota musi być większa niż 0.");
        }
        if (category == null || category.isBlank()) {
            throw new InvalidTransactionException("Kategoria nie może być pusta.");
        }

        this.title = title;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.type = type;
    }

    // Геттери
    public String getTitle() {
        return title;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getDate() {
        return date;
    }

    public Type getType() {
        return type;
    }

    // Для виводу
    @Override
    public String toString() {
        return "[" + date + "] " + type + " | " + title + " (" + category + "): " + amount + " zł";
    }
}