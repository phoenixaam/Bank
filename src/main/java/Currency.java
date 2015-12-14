class Currency {
    private final String name;
    private final String code;
    private final String digitalCode;
    private double rate;


    public Currency(String name, String code, String digitalCode, double rate) {
        this.name = name;
        this.code = code;
        this.digitalCode = digitalCode;

        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public String getDigitalCode() {
        return digitalCode;
    }

    public String getCode() {
        return code;

    }

    public double getRate() {

        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public static double convertTo(Currency from, Currency to, double sum) {
        if (to.getRate() != 0) {
            return sum * from.getRate() / to.getRate();
        }
        return 0;
    }
}
