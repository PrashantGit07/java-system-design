// You are building a navigation system for a travel app. The app should be able to calculate routes using different strategies such as:
// Fastest Route (minimize travel time)
// Shortest Route (minimize distance)
// Scenic Route (prefer highways with views, avoid industrial areas)
// How would you design this using the Strategy Pattern so that new routing strategies (like “Avoid Tolls” or “Eco‑Friendly Route”) can be added later without changing the core navigation logic?

// // strategy interface
// interface calculateRoute {
//     abstract void calculate();
// }

// // fastest route calculation - concrete strategy

// class fastestRoute implements calculateRoute {
//     @Override
//     public void calculate() {
//         System.out.println("This is fastest route");
//     }
// }

// // shortest route - concrete strategy

// class shortestRoute implements calculateRoute {
//     @Override
//     public void calculate() {
//         System.out.println("this is shortest route");
//     }
// }

// // scenic route - concrete strategy

// class scenicRoute implements calculateRoute {
//     @Override
//     public void calculate() {
//         System.out.println("this is scenic route");
//     }
// }

// // client uses differnt strategies

// class navigationSystem {
//     calculateRoute calculateRoute;

//     public navigationSystem(calculateRoute calculateRoute) {
//         this.calculateRoute = calculateRoute;
//     }

//     public void calculateRoutes() {
//         calculateRoute.calculate();
//     }
// }

// public class StrategyPattern {
//     public static void main(String[] args) {
//         navigationSystem nv = new navigationSystem(new fastestRoute());
//         nv.calculateRoutes();

//         navigationSystem nv2 = new navigationSystem(new shortestRoute());
//         nv2.calculateRoutes();
//     }
// }

//enhanced more, like we have differnt nvigation systems (car navigation , bike navigation , train navigation)

// strategy interface
interface calculateRoute {
    abstract void calculate();
}

// fastest route calculation - concrete strategy

class fastestRoute implements calculateRoute {
    @Override
    public void calculate() {
        System.out.println("This is fastest route");
    }
}

// shortest route - concrete strategy

class shortestRoute implements calculateRoute {
    @Override
    public void calculate() {
        System.out.println("this is shortest route");
    }
}

// scenic route - concrete strategy

class scenicRoute implements calculateRoute {
    @Override
    public void calculate() {
        System.out.println("this is scenic route");
    }
}

// abstract navigation system

abstract class navigationSystem {
    protected calculateRoute calculateRoute;

    public void calculateRoutesForNavigations() {
        calculateRoute.calculate();
    }

    // runtime strategy change
    public void changeStrategy(calculateRoute c) {
        this.calculateRoute = c;
    }

    abstract void display();
}

class bikeNavigation extends navigationSystem {

    public bikeNavigation() {
        calculateRoute = new fastestRoute();
    }

    @Override
    public void display() {
        System.out.println("this is bike");
    }
}

class carNavigation extends navigationSystem {
    public carNavigation() {
        calculateRoute = new shortestRoute();
    }

    @Override
    public void display() {
        System.out.println("this is car navigation");
    }
}

public class StrategyPattern {
    public static void main(String[] args) {

        navigationSystem bk = new bikeNavigation();
        navigationSystem cr = new carNavigation();

        bk.calculateRoutesForNavigations();
        bk.display();

        cr.calculateRoutesForNavigations();
        cr.display();
    }
}
