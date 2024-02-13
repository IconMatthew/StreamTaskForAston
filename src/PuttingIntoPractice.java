import java.util.Arrays;
import java.util.List;

public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // 1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей к большей).
        List<Transaction> sortedLater2011 = transactions
                .stream()
                .filter(e -> e.getYear() == 2011)
                .sorted((o1, o2) -> o1.getValue() - o2.getValue())
                .toList();

        // 2. Вывести список неповторяющихся городов, в которых работают трейдеры.
        List<String> distinctiveCities = transactions
                .stream()
                .map(e -> e.getTrader().getCity())
                .distinct()
                .toList();

        // 3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.
        List<Trader> sortedCambridgeTraders = transactions
                .stream()
                .map(Transaction::getTrader)
                .sorted((o1, o2) -> o1.getName().compareTo(o2.getName())).toList();

        // 4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном порядке.
        String sortedTradersByName = transactions
                .stream()
                .map(Transaction::getTrader)
                .distinct()
                .sorted((o1, o2) -> o1.getName().compareTo(o2.getName()))
                .map(Trader::getName)
                .toList()
                .toString();

        // 5. Выяснить, существует ли хоть один трейдер из Милана.
        boolean thereIsATraderFromMilan = transactions
                .stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));

        // 6. Вывести суммы всех транзакций трейдеров из Кембриджа.
        int sumOfAllCambridgeTradersTransactionValues = transactions
                .stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .mapToInt(transaction -> transaction.getValue())
                .sum();

        // 7. Какова максимальная сумма среди всех транзакций?
        int maxSum = transactions
                .stream()
                .mapToInt(transaction -> transaction.getValue())
                .max().getAsInt();

        // 8. Найти транзакцию с минимальной суммой.
        Transaction transactionWithMinValue = transactions
                .stream()
                .sorted((o1, o2) -> o1.getValue() - o2.getValue())
                .findFirst()
                .get();
        
        System.out.println(transactionWithMinValue);
    }
}