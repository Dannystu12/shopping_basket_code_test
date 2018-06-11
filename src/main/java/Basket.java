import java.util.HashMap;

public class Basket {
    private HashMap<Item, Integer> items;
    private Customer customer;
    private final double LOYALTY_DISCOUNT = 0.02;
    private final double VALUE_DISCOUNT = 0.1;

    public Basket(Customer customer){
        items = new HashMap<>();
        this.customer = customer;
    }

    public Customer getCustomer(){
        return customer;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public int countItems(){
        int total = 0;
        for(int qty : items.values()){
            total += qty;
        }
        return total;
    }

    public void add(Item item, int qty){
        if(qty <= 0) return;
        int startingQty = items.getOrDefault(item, 0);
        int newQty = startingQty + qty;
        items.put(item, newQty);
    }

    public void removeItem(Item item, int qty){
        if(qty <= 0) return;
        int startingQty = items.getOrDefault(item, -1);
        if(startingQty == -1) return;
        if(qty > startingQty) throw new IllegalArgumentException("Error: quantity to remove cannot exceed quantity in basket");
        int newQty = startingQty - qty;

        if(newQty == 0){
            items.remove(item);
        }else{
            items.put(item, newQty);
        }
    }

    public void clear(){
        items.clear();
    }

    public double getGrossTotal(){
        double total = 0;
        for(Item item : items.keySet()){
            double lineTotal = item.getPrice() * items.get(item);
            total += lineTotal;
        }
        return total;
    }

    public double getDiscount(double total){
        double discount = calculateBogoffDiscount();
        discount += calculateValueDiscount(total - discount);
        discount += calculateLoyaltyDiscount(total - discount);
        return discount;
    }

    public double getNetTotal(){
        double grossTotal = getGrossTotal();
        return grossTotal - getDiscount(grossTotal);
    }

    public int getQty(Item item){
        return items.getOrDefault(item, 0);
    }

    private double calculateBogoffDiscount(){
        double total = 0;
        for(Item item : items.keySet()){
            if(!item.isBogof()) continue;
            int qty = items.getOrDefault(item, 0);
            total += qty / 2 * item.getPrice();
        }
        return total;
    }

    private double calculateLoyaltyDiscount(double total){
        return customer.hasLoyaltyCard() ? total * LOYALTY_DISCOUNT: 0;
    }

    private double calculateValueDiscount(double total){
        return total > 20 ? total * VALUE_DISCOUNT : 0;
    }
}
