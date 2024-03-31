public class Money {
    private int amount;
    private String currency;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Money(int amount, String currency) {
        if (amount < 0) {
            throw new IllegalArgumentException(
                    "illegal amount: [" + amount +"]"
            );
        }
        if (currency == null || currency.isEmpty()) {
            throw new IllegalArgumentException(
                    "illega currency: [" + currency + "]"
            );
        }
        this.amount = amount;
        this.currency = currency;
    }
}
