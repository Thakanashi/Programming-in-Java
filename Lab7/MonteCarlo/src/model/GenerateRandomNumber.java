package model;

import java.util.Random;

public class GenerateRandomNumber {

    private static Random random = new Random();
    public static double generate(){
        double x = -8 + (8 - (-8)) * random.nextDouble();
        return x;
    }

}
