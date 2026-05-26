import java.util.ArrayList;
import java.util.List;

class order{
    private int orderId;
    private String orderName;

    private List<String>OrderDetails;

    public order(int orderId , String orderName){
        this.orderId = orderId;
        this.orderName = orderName;
        this.OrderDetails = new ArrayList<>();
    }

    public void makeOrder(int orderId , String orderName){
        System.out.println("Order recieved for ->");
        System.out.println("Order Id -> " + orderId);
        
        OrderDetails.add(orderName);
    }

    public void getOrders(){
        for(String order : OrderDetails){
            System.out.println("Order details here -> " + order);
        }
    }

    
}

public class FoodApp{
    public static void main(String[] args){
        order o1 = new order(1, "Burger");
        o1.makeOrder(01, "Samosa");
        o1.makeOrder(02, "Idli");

        o1.getOrders();
    }
}