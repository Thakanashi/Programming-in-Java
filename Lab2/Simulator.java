import java.util.ArrayList;
import java.util.List;

public class Simulator {

    public static void main(String[] args) {

        FulfillmentCenterContainer m=new FulfillmentCenterContainer();
        FulfillmentCenterContainer  m1= new FulfillmentCenterContainer();
        List<Item> Pom = new ArrayList<>();
        List<FulfillmentCenter> pList = new ArrayList<>();
        Item i1 =new Item("stojak", 1,5,ItemCondition.NEW);
        Item i2 = new Item("laptop",1,1,ItemCondition.USED);
        Item i3 = new Item("krzeslo",1,30,ItemCondition.NEW);
        Item i4 = new Item("cukierki",1,11,ItemCondition.NEW);

        FulfillmentCenter tmp=m.addCenter("Magazyn1",100);

        FulfillmentCenter spareContainer = m1.addCenter("Magazyn2",50);
        //dodawanie elementu
        tmp.addProduct(i1);
        tmp.addProduct(i2);
        tmp.addProduct(i3);
        tmp.addProduct(i4);

        //usuwanie badz zmniejszanie o 1
        tmp.getProduct(i4);
        //usuwanie itemu
        tmp.removeProduct(i4);
        //search
        System.out.println(tmp.search("krzeslo"));
        //partial String
        Pom = tmp.searchPartialString("op");
        System.out.println(Pom);
        //count by Condition
        System.out.println(tmp.countByCondition(ItemCondition.NEW));
        //podsumowanie
        tmp.summary();
        //sort by name
        Pom = tmp.sortByName();
        for(Item i:Pom){
            System.out.println(i);
        }
        //
        Pom=tmp.sortByAmount();
       for(Item i:Pom)
        {
             i.print();
        }
        System.out.println(tmp.max());

        //tmp111.stream().foreach(System.out::println());
        //summary
        m1.summary();
        m.summary();
        m.removeCenter("Magazyn1");
        pList=m.findEmpty();
        for(FulfillmentCenter i:pList)
        {
            System.out.println(i);
        }

    }
}
