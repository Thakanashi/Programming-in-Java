import java.util.Comparator;

public class Item implements Comparable<Item> {

    private String name;
    private ItemCondition condition;
    private double mass;
    private int quantity;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", condition=" + condition +
                ", mass=" + mass +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return name.equals(item.name);
    }

    @Override
    public int compareTo(Item o) {
            return name.compareTo(o.name);
   }

    public Item(String n, double m, int q, ItemCondition i){
        this.name = n;
        this.mass = m;
        this.quantity = q;
        this.condition= i;
    }

    public void print(){
        System.out.println("Name: " + name);
        System.out.println("ItemCondition: "+ condition);
        System.out.println("Mass: " + mass);
        System.out.println("Quantity: " + quantity);
        System.out.println();

    }

    public String getName(){
        return this.name;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public ItemCondition getCondition() {
        return condition;
    }

    public double getMass() {
        return mass;
    }



}
