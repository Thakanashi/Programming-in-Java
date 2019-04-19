public class Calculator {

    public static void chooseFigure(){
        System.out.println("Choose figure:");
        System.out.println("1: Triangle");
        System.out.println("2: Square");
        System.out.println("3: Circle");
        System.out.println("4: Exit");
        choice(UserInput.fillInData());
    }

    public static void choice(double x){
        int tmp = (int)x;
        while (true) {
            switch(tmp) {
                case 1:
                    enterDimensionsOfTriangle();
                    break;
                case 2:
                    enterDimensionOfSquare();
                    break;
                case 3:
                    enterDimensionOfCircle();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Error");
                    chooseFigure();
            }


        }
    }

    public static void enterDimensionsOfTriangle(){
        System.out.println("Please enter dimensions of triangle: ");
        double [] arr = new double[3];
        arr[0] = UserInput.fillInData();
        arr[1] = UserInput.fillInData();
        arr[2] = UserInput.fillInData();
        try {
            Triangle T = new Triangle(arr[0],arr[1],arr[2]);
            T.calculateArea();
            T.calculatePerimeter();
            T.print();
            chooseFigure();
        }
        catch (IllegalArgumentException e) {
            System.out.println("You cannot make a triangle with these segments");
            enterDimensionsOfTriangle();
        }


        }





    public static void enterDimensionOfCircle() {
        System.out.println("Please enter the length of circle radius");
        Circle C = new Circle(UserInput.fillInData());
        C.calculateArea();
        C.calculatePerimeter();
        C.print();
        chooseFigure();
    }

    public static void enterDimensionOfSquare(){
        System.out.println("Please give the length of side square");
        Square S = new Square(UserInput.fillInData());
        S.calculateArea();
        S.calculatePerimeter();
        S.print();
        chooseFigure();
    }


    public static void main(String[] args) {

            chooseFigure();
        }
    }

