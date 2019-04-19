import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FulfillmentCenterContainer {

    Map<String, FulfillmentCenter> M = new HashMap<>();

    public FulfillmentCenter addCenter(String s,double d){
        FulfillmentCenter tmp = new FulfillmentCenter(s,d);
        M.put(s, tmp);
        return tmp;
    }

    public void removeCenter(String s){
        M.remove(s);
    }

    public List<FulfillmentCenter> findEmpty(){
        List<FulfillmentCenter> L = new ArrayList<>();
        for(FulfillmentCenter fulfillmentCenter :M.values())
        {
            if(fulfillmentCenter.getMaxCapacity() ==0)
            {
                L.add(fulfillmentCenter);
            }
        }
        return L;
    }
    public void summary(){
        List<FulfillmentCenter> L = new ArrayList<>();
        for(FulfillmentCenter fulfillmentCenter:M.values())
        {
            System.out.println("Nazwa magazynu: "+fulfillmentCenter.getNameOfWarehouse());
            System.out.println("Procentowe zapełnienie: "+ fulfillmentCenter.getTempCapacity()/fulfillmentCenter.getMaxCapacity()*100+"%");


        }

    }

}
