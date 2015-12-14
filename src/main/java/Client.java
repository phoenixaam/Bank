import java.util.ArrayList;
import java.util.List;

public class Client {
    private final String name;
    private final String passportNumber;
    private final List<Account> accounts;

    public Client(String name, String passportNumber) {
        this.name = name;
        this.passportNumber = passportNumber;
        this.accounts = new ArrayList<Account>();
    }

    public Account openAccount(Bank bank, Currency currency, AccountType type) {
        Account account = Bank.generateNewAccount(bank, currency, type);
        accounts.add(account);
        bank.addClient(this);
        return account;
    }

    public boolean fill(Account account, double sum) {
        if (account != null) {
            account.setSum(account.getSum() + Currency.round(sum, 2));
            System.out.println(name + " successfully filled up the account " + account.getNumber() + ", sum: " + Currency.round(sum, 2) +
                    " " + account.getCurrency().getCode() + ".");
            return true;
        } else {
            return false;
        }
    }

    public boolean withdraw(Account account, double sum) {
        if (!accounts.contains(account)) {
            System.out.println(name + ", account " + account.getNumber() + " is not your own account!");
            return false;
        } else if (account.getSum() < sum) {
            System.out.println(name + ", you don't have enough money on the account " + account.getNumber() + "!");
            return false;
        } else {
            account.setSum(account.getSum() - Currency.round(sum, 2));
            System.out.println(name + " successfully withdrew sum: " + Currency.round(sum, 2) + " " + account.getCurrency().getCode() +
                    " from the account " + account.getNumber() + ".");
            return true;
        }
    }

    public boolean send(Account from, Account to, double sum) {
        double newSum;
        if (!accounts.contains(from)) {
            System.out.println("It's not your account: " + from + ", you can move money only from own accounts!");
            return false;
        } else if (to == null) {
            System.out.println("Destination account not exist!");
            return false;
        } else if (from.getSum() < sum) {
            System.out.println(name + ", you don't have enough money on the account " + from.getNumber() + "!");
            return false;
        } else if (from.getCurrency() != to.getCurrency()) {
            newSum = Currency.convertTo(from.getCurrency(), to.getCurrency(), sum);
        } else {
            newSum = sum;
        }
        from.setSum(from.getSum() - Currency.round(sum, 2));
        to.setSum(to.getSum() + Currency.round(newSum, 2));
        System.out.println(name + " successfully sent sum: " + Currency.round(sum, 2) + " " + from.getCurrency().getCode() +
                " from the account " + from.getNumber() + " to the account " + to.getNumber() + ".");
        return true;
    }

    public void accountsStatement() {
        System.out.println("Account Statement for " + name + ":");
        for (Account account :
                accounts) {
            System.out.println("Account: " + account.getNumber() + ", type: " + account.getType().toString() + ". Balance: " +
                    account.getSum() + " " + account.getCurrency().getCode() + " in the " + account.getBank().getName() + ".");
        }
    }
}
