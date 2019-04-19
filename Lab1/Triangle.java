import java.util.Arrays;

public class Triangle extends Figure {

    double a;
    double b;
    double c;


   public Triangle(double x, double y, double z){

       if(!Triangle.ifTriangle(x, y, z))
           throw new IllegalArgumentException();

       this.a = x;
       this.b = y;
       this.c = z;
   }

    public static boolean ifTriangle(double ...arr){
        Arrays.sort(arr);
        return (arr[2]<arr[1]+arr[0]);
    }

    @Override
    public double calculateArea() {
        double p = (a+b+c)/2.0;
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }

    @Override
    public double calculatePerimeter() {
        return a+b+c;
    }

    @Override
    public void print() {
        System.out.println("Figure: Triangle");
        System.out.println("Lengths of sides: " + a + " " + b + " " + c);
        System.out.println("Triangle area: " + calculateArea());
        System.out.println("Triangle perimeter: " + calculatePerimeter());
        System.out.println();
    }
}
