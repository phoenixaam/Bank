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
            account.setSum(account.getSum() + sum);
            System.out.println(name + " successfully filled up the account " + account.getNumber() + ", sum: " + sum +
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
            account.setSum(account.getSum() - sum);
            System.out.println(name + " successfully withdrew sum: " + sum + " " + account.getCurrency().getCode() +
                    "from the account " + account.getNumber() + ".");
            return true;
        }
    }

    public boolean send(Account from, Account to, double sum) {
        if (!accounts.contains(from)) {
            System.out.println("It's not your account: " + from + ", you can move money only from own accounts!");
            return false;
        } else if (to == null) {
            System.out.println("Destination account not exist!");
            return false;
        } else if (from.getSum() < sum) {
            System.out.println(name + ", you don't have enough money on the account " + from.getNumber() + "!");
            return false;
        } else {
            from.setSum(from.getSum() - sum);
            to.setSum(to.getSum() + sum);
            System.out.println(name + " successfully sent sum: " + sum + " " + from.getCurrency().getCode() +
                    " from the account " + from.getNumber() + " to the account " + to.getNumber() + ".");
            return true;
        }
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
