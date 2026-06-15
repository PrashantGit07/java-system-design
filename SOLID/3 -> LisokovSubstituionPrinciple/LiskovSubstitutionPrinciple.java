
//it says that , the subclass (child class) should be able to substitue super class (parent class)
//means , if method is written in parent class , then client should be able to call that method from the child class as well

//LSP - not followed

// class Bird {
//     public void fly() {
//         System.out.println("Flying...");
//     }
// }

// class Sparrow extends Bird {
// }

// class Penguin extends Bird {
//     @Override
//     public void fly() {
//         throw new UnsupportedOperationException("Penguins can't fly");
//     }
// }

// class Main {
//     public static void main(String[] args) {
//         Bird bird = new Penguin();
//         bird.fly(); // Runtime error
//     }
// }

//LSP followed

class Bird {
    public void eat() {
        System.out.println("Eating...");
    }
}

class FlyingBird extends Bird {
    public void fly() {
        System.out.println("Flying...");
    }
}

class Sparrow extends FlyingBird {
}

class Penguin extends Bird {
}

class Main {

}

public class LiskovSubstitutionPrinciple {
    public static void main(String[] args) {

        FlyingBird sparrow = new Sparrow();
        sparrow.fly();

        Penguin penguin = new Penguin();
        penguin.eat();
    }
}
