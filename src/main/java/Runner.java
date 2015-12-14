public class Runner {
    public static void main(String[] args) {
//        prepare data for example:
        Bank bankAval = new Bank("Raiffeisen Bank Aval", "300335");
        Bank bankPivdenny = new Bank("Bank Pivdenny", "328209");
        Bank bankPrivat = new Bank("Privat Bank", "305299");

        Currency usd = new Currency("American Dollar", "USD", "840", 1);
        Currency eur = new Currency("Euro", "EUR", "978", 1.09);
        Currency hrn = new Currency("Hrivna", "UAH", "980", 0.04);

        Client client1 = new Client("Vasiliy Pupkin", "KE562356");
        Client client2 = new Client("Petr Vasechkin", "KC223322");
        Client client3 = new Client("Gadia Hrenova", "KP454556");

        Account account1client1 = client1.openAccount(bankAval, usd, AccountType.DEPOSIT);
        Account account2client1 = client1.openAccount(bankAval, eur, AccountType.DEPOSIT);
        Account account3client1 = client1.openAccount(bankPivdenny, usd, AccountType.CURRENT);
        Account account1client2 = client2.openAccount(bankPrivat, usd, AccountType.DEPOSIT);
        Account account2client2 = client2.openAccount(bankAval, usd, AccountType.CURRENT);
        Account account1client3 = client3.openAccount(bankPivdenny, hrn, AccountType.CURRENT);
        Account account2client3 = client3.openAccount(bankPrivat, eur, AccountType.DEPOSIT);

        client1.fill(account1client1, 100);
        client1.fill(account2client1, 200);
        client3.fill(account2client3, 300);

        client1.withdraw(account1client1, 50);
        client2.withdraw(account2client2, 100);

        client1.send(account2client1, account2client3, 100);
        client3.send(account2client3, account1client3, 20);

        client1.accountsStatement();
        client2.accountsStatement();
        client3.accountsStatement();

    }
}
