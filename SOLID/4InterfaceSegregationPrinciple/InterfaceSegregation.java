//Definition: A class should not be forced to implement methods it does not need.

//LSP - not followed

// interface Worker {
//     void work();

//     void eat();
// }

// class HumanWorker implements Worker {

//     @Override
//     public void work() {
//         System.out.println("Human working");
//     }

//     @Override
//     public void eat() {
//         System.out.println("Human eating");
//     }
// }

// class RobotWorker implements Worker {

//     @Override
//     public void work() {
//         System.out.println("Robot working");
//     }

//     @Override
//     public void eat() {
// Robot doesn't eat
//         throw new UnsupportedOperationException();
//     }
// }

//Problem:
// Robot is forced to implement eat().
// ISP is violated.

//LSP followed

interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

class HumanWorker implements Workable, Eatable {

    @Override
    public void work() {
        System.out.println("Human working");
    }

    @Override
    public void eat() {
        System.out.println("Human eating");
    }
}

class RobotWorker implements Workable {

    @Override
    public void work() {
        System.out.println("Robot working");
    }
}

class Mains {
    public static void main(String[] args) {

        HumanWorker human = new HumanWorker();
        human.work();
        human.eat();

        RobotWorker robot = new RobotWorker();
        robot.work();
    }
}

public class InterfaceSegregation {

}
