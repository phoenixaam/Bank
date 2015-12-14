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
            return round((sum * from.getRate() / to.getRate()), 2);
        }
        return 0;
    }

    public static double round(double value, int amount) {
        String koefStr = "1";
        for (int i = 0; i < amount; i++) {
            koefStr = koefStr + "0";
        }
        int koef = Integer.parseInt(koefStr);
        System.out.println(koefStr);
        System.out.println(koef);
        return ((double) Math.round(value * koef)) / koef;
    }
}
