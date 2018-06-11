public class Customer {
    private String name;
    private boolean hasLoyaltyCard;

    public Customer(String name, boolean hasLoyaltyCard) {
        this.name = name;
        this.hasLoyaltyCard = hasLoyaltyCard;
    }

    public String getName() {
        return name;
    }

    public boolean hasLoyaltyCard() {
        return hasLoyaltyCard;
    }
}
