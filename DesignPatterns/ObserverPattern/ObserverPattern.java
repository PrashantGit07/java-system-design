//when we want to update all the objects which are connected to one object at once
//means event happened on one entity and its related connected entities are automatically updated
//used in pub-sub systems

import java.util.ArrayList;
import java.util.List;

abstract class StockObservable {
    abstract void notifyObservers();

    int price = 0;
}

abstract class StockObserver {
    abstract void updateUsers(int updatedPrice);
}

class ConcreteStockObservable extends StockObservable {

    private List<StockObserver> observersList = new ArrayList<>();

    public void AddToList(StockObserver observerName) {
        observersList.add(observerName);
    }

    public void RemoveFromList(StockObservable observerName) {
        observersList.remove(observerName);
    }

    @Override
    public void notifyObservers() {
        for (StockObserver observer : observersList) {
            observer.updateUsers(price);
        }
    };

    public void updatePrice(int updatedPrice) {
        this.price = updatedPrice;
        notifyObservers();
    }
}

class ConcreteStockObserver extends StockObserver {

    String userName;

    public ConcreteStockObserver(String userName) {
        this.userName = userName;
    }

    @Override
    public void updateUsers(int price) {
        System.out.println("Updated Price -> " + price);
        System.out.println("Stock Price Update sent to -> " + userName);
    }
}

class EmailObserver extends StockObserver {

    String email;

    public EmailObserver(String email) {
        this.email = email;
    }

    @Override
    public void updateUsers(int price) {
        System.out.println("User updated via email -> " + email + "Price -> " + price);
    }
}

public class ObserverPattern {

    public static void main(String[] args) {
        ConcreteStockObservable concreteStockObservable = new ConcreteStockObservable();

        StockObserver adtiya = new ConcreteStockObserver("Aditya");
        StockObserver Rahul = new ConcreteStockObserver("Rahul");
        StockObserver Nitin = new EmailObserver("nitin@gmail.com");

        concreteStockObservable.AddToList(adtiya);
        concreteStockObservable.AddToList(Rahul);
        concreteStockObservable.AddToList(Nitin);

        concreteStockObservable.updatePrice(100);

    }
}
