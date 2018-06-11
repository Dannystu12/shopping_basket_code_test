public class Item {
    private final String name;
    private final double price;
    private final boolean bogof;

    public Item(String name, double price, boolean bogof) {
        if(price < 0) throw new IllegalArgumentException("Error Items Cannot Have Price Less Than 0");
        this.name = name;
        this.price = price;
        this.bogof = bogof;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isBogof(){
        return bogof;
    }
}
