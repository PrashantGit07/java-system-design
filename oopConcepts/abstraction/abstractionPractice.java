
abstract class Shape {

    private String shape;

    public Shape(String shape) {

        this.shape = shape;
    }

    abstract double calculateArea();

    public void display() {
        System.out.println("Shape -> " + shape);

    }
}

class Cirlce extends Shape {
    private double radius;

    public Cirlce(double radius) {
        super("Circle");
        this.radius = radius;
    }

    @Override
    double calculateArea() {

        if (radius != 0) {

            return 3.14 * radius * radius;
        } else {
            return 0.0;
        }
    }

}

class Reactangle extends Shape {
    private double w;
    private double h;

    public Reactangle(double w, double h) {
        super("Rectangle");

        this.w = w;
        this.h = h;
    }

    @Override

    double calculateArea() {
        if (w != 0 && h != 0) {
            return w * h;
        } else {
            return 0.0;
        }
    }
}

public class abstractionPractice {
    public static void main(String[] args) {

        Shape c1 = new Cirlce(10.5);
        System.out.println("Area of Cirlce -> " + c1.calculateArea());
        c1.display();

        Shape r1 = new Reactangle(5, 8);
        System.out.println("Area of rectangle ->" + r1.calculateArea());
        r1.display();
    }
}
