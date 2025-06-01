import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionStats {

    public static Map<String, Double> sumByCategory(List<Transaction> transactions) {
        Map<String, Double> result = new HashMap<>();

        for (Transaction t : transactions) {
            String cat = t.getCategory();
            double amt = t.getType() == Transaction.Type.INCOME ? t.getAmount() : -t.getAmount();
            result.put(cat, result.getOrDefault(cat, 0.0) + amt);
        }

        return result;
    }

    public static double totalIncome(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getType() == Transaction.Type.INCOME)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public static double totalExpenses(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getType() == Transaction.Type.EXPENSE)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }
}