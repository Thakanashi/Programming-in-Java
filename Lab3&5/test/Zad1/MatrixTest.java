package Zad1;

import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixTest {

    @Test
    public void matrixAddition() {
        Matrix<Integer> t1 = new Matrix<>(2,2);
        Matrix<Integer> t2 = new Matrix<>(2,2);
        Matrix<Integer> result = new Matrix<>(2,2);
        Matrix<Integer> tmp = new Matrix<>(2,2);
        t1.getInterior()[0][0]=1;
        t1.getInterior()[0][1]=2;
        t1.getInterior()[1][0]=3;
        t1.getInterior()[1][1]=5;

        t2.getInterior()[0][0]=1;
        t2.getInterior()[0][1]=4;
        t2.getInterior()[1][0]=1;
        t2.getInterior()[1][1]=2;

        result.getInterior()[0][0]=2;
        result.getInterior()[0][1]=6;
        result.getInterior()[1][0]=4;
        result.getInterior()[1][1]=7;

        tmp=Matrix.matrixAddition(t1,t2);
        for(int i=0; i<2; i++){
            for (int j=0; j<2;j++){
                assertEquals((int)tmp.getInterior()[i][j],(int)result.getInterior()[i][j]);
            }
        }

    }
}