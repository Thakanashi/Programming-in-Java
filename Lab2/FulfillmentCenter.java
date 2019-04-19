import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FulfillmentCenter  {
    private String nameOfWarehouse;
    private List<Item> L = new ArrayList<Item>();
    private double maxCapacity;
    private double tempCapacity;

    public double getTempCapacity() {
        return tempCapacity;
    }

    public String getNameOfWarehouse() {
        return nameOfWarehouse;
    }

    public double getMaxCapacity() {
        return maxCapacity;
    }

    public FulfillmentCenter(String n, double m){

        this.nameOfWarehouse = n;
        this.maxCapacity = m;
        this.tempCapacity = 0;
    }

    public  void addProduct(Item k){
        if(k.getQuantity() ==0){
            System.out.println("Nie mozesz dodac produktu w ilosci 0 sztuk");
        }
        else{
            if(tempCapacity+k.getQuantity()<maxCapacity){
                int index = L.indexOf(k);
                if(index == -1)
                    L.add(k);
                else {
                    Item existing = L.get(index);
                    existing.setQuantity(k.getQuantity() + existing.getQuantity());
                }

                tempCapacity += k.getQuantity();
            }
            else
                System.err.println("Przekroczono ilosc przedmiotow w magazynie");
        }


    }

    public void getProduct(Item k){

        int index = L.indexOf(k);
        Item existing = L.get(index);
        if(existing.getQuantity()-1>0) {
            existing.setQuantity(existing.getQuantity()-1);
            tempCapacity-=1;
            System.out.println("Pomyslnie zmniejszono ilosc produktów o 1");
        }
        else{
            L.remove(existing);
            tempCapacity-=1;
            System.out.println("Twój produkt został usunięty");
        }


    }

    public void removeProduct(Item i){
        int index = L.indexOf(i);       //wyszukiwanie indeksu przedmiotu ktory chcemy usunac
        L.remove(L.get(index));
        tempCapacity-=i.getQuantity();
        System.out.println("Twój produkt został usunięty!");

    }

    public Item search(String s) {
       return Collections.max(L, (l1,l2) -> {
            return (l1.getName().equals(s)) ? 1 :
                    (l2.getName().equals(s) ? -1 : 0);
       });
    }

    public List<Item> searchPartialString(String s){
        List<Item> tmp = new ArrayList<>();
        for(Item i:L){
            if(i.getName().contains(s)) {
                tmp.add(i);
            }
        }
        return tmp;
    }

    public int countByCondition(ItemCondition i) {
        int licznik = 0;
        for(Item k:L)
        {
            if(k.getCondition() == i)
                licznik++;
        }

        return licznik;
    }

    public List<Item> sortByName() {

        Collections.sort(L,Comparator.comparing(Item::getName));
        return L;
    }

    public List<Item> sortByAmount(){
        List<Item> tmp = new ArrayList<>(L);
        Collections.sort(tmp, Comparator.comparingInt(Item::getQuantity).reversed());
            return tmp;
    }

    public Item max(){
        List<Item> tmp = new ArrayList<>(L);
        return Collections.max(tmp,Comparator.comparingInt(Item::getQuantity));



    }

    public void summary(){

        System.out.println("Ilosc prouktow w magazynie: " + tempCapacity);
        for(Item i:L)
        {
            i.print();
        }
    }



}
