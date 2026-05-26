// Design the order management module for a food delivery app like Swiggy or Zomato where an order moves through different states such as placed, preparing, delivered, or cancelled


enum OrderStatus{
    PLACED,
    CONFIRMED,
    SHIPPED,
    OUT_FOR_DELIVERY,
    CANCELLED
}

class Order{
    int orderId;
    String customerName;
    OrderStatus status;

    Order(int orderId , String customerName){
        this.orderId = orderId;
        this.customerName = customerName;
        this.status = OrderStatus.PLACED;
    }

    public void getOrderDetails(){
        System.out.println("Order Id ->" + orderId);
        System.out.println("Customer Name -> " + customerName);
        System.out.println("Order Status -> " + status);
    }

    public void changeOrderStatus(OrderStatus status){

    if(this.status == OrderStatus.SHIPPED 
        && status == OrderStatus.CANCELLED){

        System.out.println("Can not cancel the order as the order is shipped");
        return;
    }

        this.status = status;
    }
}

public class enumPractice{
    public static void main(String[] args){
        Order o1 = new Order(01, "Josh");

        o1.getOrderDetails();


        o1.changeOrderStatus(OrderStatus.CONFIRMED);
        o1.getOrderDetails();

        o1.changeOrderStatus(OrderStatus.SHIPPED);
        o1.getOrderDetails();

        o1.changeOrderStatus(OrderStatus.CANCELLED);
        o1.getOrderDetails();


        o1.changeOrderStatus(OrderStatus.OUT_FOR_DELIVERY);
        o1.getOrderDetails();

        
    }
}