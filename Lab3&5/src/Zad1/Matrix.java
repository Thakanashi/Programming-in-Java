package Zad1;

import java.util.Random;
import java.util.Scanner;


public class Matrix<T>   {
    public Object[][] getInterior() {
        return interior;
    }

    private int rows,columns;
    private Object interior[][];

    public Matrix(int x, int y){
        this.rows = x;
        this.columns = y;
        @SuppressWarnings("unchecked") T[][] array = (T[][]) new Object[x][y];
        interior = array;

    }


    public static Integer add(Integer value1, Integer value2)
    {
        return value1 + value2;
    }



    public static void createMatrices(){
        Scanner scanner = new Scanner(System.in);
        Random gen = new Random();
        int a,b,c,d;
        System.out.println("Enter the number of rows and columns of first matrix");
        a = scanner.nextInt();
        b = scanner.nextInt();
        System.out.println("Enter the number of rows and columns of second matrix");
        c = scanner.nextInt();
        d = scanner.nextInt();

        if(a==c && b==d){
            Matrix<Integer> m1 = new Matrix<>(a,b);
            Matrix<Integer> m2 = new Matrix<>(c,d);

            for(int i=0; i<a; i++)
            {
                for (int j=0; j<b; j++)
                {
                    m1.interior[i][j]= gen.nextInt(100)+1;
                    m2.interior[i][j]= gen.nextInt(100)+1;
                }
            }

            System.out.println();
            matrixAddition(m1,m2);

        }
        else {
            System.out.println("Dimensions of first matrix must be exactly the same as the second one!");
            createMatrices();
        }




    }


    public static <T> Matrix matrixAddition(Matrix<? extends T> m1,Matrix<? extends T> m2){

        if(m1.getRows()==m2.getRows() && m1.getColumns()==m2.getColumns())
        {
            Matrix<Integer> result = new Matrix<>(m1.getRows(),m1.getColumns());
            System.out.println("Result: ");
            for(int i=0; i<m1.getRows();++i)
            {
                for(int j=0; j<m1.getColumns(); ++j)
                {
                    int tmpM1= (Integer) m1.interior[i][j];
                    int tmpM2 = (Integer) m2.interior[i][j];
                    result.interior[i][j] =add(tmpM1,tmpM2);
                    System.out.print(result.interior[i][j]+" ");
                }
                System.out.println();
            }

            return result;
        }
        else
            throw new IllegalArgumentException ("Nie można dodawać macierzy o różnych wymiarach!") ;


    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;

    }


}

