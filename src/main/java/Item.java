public class Item {
    private final String name;
    private final double price;

    public Item(String name, double price) {
        if(price < 0) throw new IllegalArgumentException("Error Items Cannot Have Price Less Than 0");
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
