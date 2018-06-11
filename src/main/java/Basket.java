import java.util.ArrayList;
import java.util.HashMap;

public class Basket {
    private HashMap<Item, Integer> items;

    public Basket(){
        items = new HashMap<>();
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

    public double getTotal(){
        double total = 0;
        for(Item item : items.keySet()){
            double lineTotal = item.getPrice() * items.get(item);
            total += lineTotal;
        }
        return total;
    }

    public int getQty(Item item){
        return items.getOrDefault(item, 0);
    }
}
