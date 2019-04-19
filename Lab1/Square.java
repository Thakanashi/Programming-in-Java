public class Square extends Figure {

    double a;

    public Square(double x){
        this.a = x;
    }

    @Override
    public double calculateArea() {
        return a*a;
    }

    @Override
    public double calculatePerimeter() {
        return 4*a;
    }

    @Override
    public void print() {
        System.out.println("Figure: Square");
        System.out.println("Length of side: "+ a);
        System.out.println("Square area: "+ calculateArea());
        System.out.println("Square perimeter: "+ calculatePerimeter());
    }
}
