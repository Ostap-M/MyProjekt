import java.time.LocalDate;

public class RecurringTransaction extends Transaction {
    private int repeatIntervalDays;

    public RecurringTransaction(String title, double amount, String category,
                                LocalDate date, Type type, int repeatIntervalDays)
            throws InvalidTransactionException {
        super(title, amount, category, date, type);
        this.repeatIntervalDays = repeatIntervalDays;
    }

    @Override
    public void print() {
        System.out.println(super.toString() + " (powtarza się co " + repeatIntervalDays + " dni)");
    }
}