import java.util.*;

public class Bank {
    private final String name;
    private final String code;
    private final List<Client> clients;
    private final Deque<Account> accounts;


    public Bank(String name, String code) {
        this.name = name;
        this.code = code;
        accounts = new ArrayDeque<Account>();
        clients = new ArrayList<Client>();

    }


    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public boolean addClient(Client client) {
        if (!clients.contains(client)) {
            return clients.add(client);
        }
        return false;
    }


    public boolean removeClient(Client client) {
        return clients.remove(client);
    }


    static Account generateNewAccount(Bank bank, Currency currency, AccountType type) {
        // 25        285302     000002563
        // acc.type  bank.code  acc.number(short)
        int newNumber = getLastAccountNumber(bank) + 1;
        String newNumberStr = String.format("%09d", newNumber);
        String strNumber = type.getCode() + bank.getCode() + newNumberStr;
        Account account = new Account(strNumber);
        account.setBank(bank);
        account.setType(type);
        account.setCurrency(currency);
        bank.accounts.add(account);
        return account;
    }

    private static int getLastAccountNumber(Bank bank) {
        int lastNumberShort = 0;
        if (bank.accounts.size() > 0) {
            String lastNumberFull = bank.accounts.peekLast().getNumber();
            String lastNumberShortStr = lastNumberFull.substring(8);
            lastNumberShort = Integer.parseInt(lastNumberShortStr);
        }
        return lastNumberShort;
    }
}
