import java.util.InputMismatchException;
import java.util.Scanner;


public class UserInput {

    public static double fillInData(){
        double x;
        while(true) {
            try{
                Scanner sc = new Scanner(System.in);
                if (sc.hasNextDouble()) {
                    x = sc.nextDouble();
                    if(x>0)
                        return x;
                    else{
                        System.out.println("Your input number must be greater than 0");
                        continue;
                    }

                }
                throw new InputMismatchException();
            }
            catch (InputMismatchException e)
            {
                System.out.println("Please enter valid number");

            }

        }
    }

}