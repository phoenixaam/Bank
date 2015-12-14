public class Account {
    private final String number;
    private AccountType type;
    private Currency currency;
    private double sum;
    private Bank bank;


    public Account(String number) {
        this.number = number;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Bank getBank() {
        return bank;
    }


    public String getNumber() {
        return number;
    }

    public AccountType getType() {
        return type;
    }

    public Currency getCurrency() {
        return currency;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}

enum AccountType {
    DEPOSIT("25"), CURRENT("26");
    private String code;

    AccountType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
