public class Circle extends Figure {

    public static final double PI = 3.141592;
    double radius;

    public Circle(double r){

        this.radius = r;

    }

    @Override
    public double calculateArea() {
        return PI*radius*radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2*PI*radius;
    }

    @Override
    public void print() {
        System.out.println("Figure: Circle");
        System.out.println("Length of radius: "+ radius);
        System.out.println("Circle area: "+ calculateArea());
        System.out.println("Circle perimeter: "+ calculatePerimeter());
    }
}
