import java.time.LocalDate;
import java.io.Serializable;

public class Transaction implements Printable, Serializable {
    private static final long serialVersionUID = 1L;
    @Override
    public void print() {

    }

    public enum Type { INCOME, EXPENSE }

    private String title;
    private double amount;
    private String category;
    private LocalDate date;
    private Type type;


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


    @Override
    public String toString() {
        return "[" + date + "] " + type + " | " + title + " (" + category + "): " + amount + " zł";
    }
}