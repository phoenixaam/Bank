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

    private int getLastAccountNumber() {
        int lastNumberShort = 0;
        if (accounts.size() > 0) {
            String lastNumberFull = accounts.peekLast().getNumber();
            String lastNumberShortStr = lastNumberFull.substring(8);
            lastNumberShort = Integer.parseInt(lastNumberShortStr);
        }
        return lastNumberShort;
    }

    public Account generateNewAccount(AccountType accountType, Currency currency, Client client) {
        // 25        285302     000002563
        // acc.type  bank.code  acc.number(short)
        int newNumber = getLastAccountNumber() + 1;
        String newNumberStr = String.format("%09d", newNumber);
        String strNumber = accountType.getCode() + getCode() + newNumberStr;
        Account account = new Account(strNumber);
        account.setBank(this);
        account.setType(accountType);
        account.setCurrency(currency);
        this.accounts.add(account);
        this.addClient(client);
        return account;
    }
}
