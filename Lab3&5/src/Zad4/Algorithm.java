package Zad4;

import java.util.*;

public class Algorithm {

    private static Scanner scanner = new Scanner(System.in);
    private static Algorithm algorithm = new Algorithm();

    public float randFloat(float min, float max) {

        Random rand = new Random();

        float result = rand.nextFloat() * (max - min) + min;
        return result;
    }

    public int [] solution (float [] arr,float target){
        int [] results = new int [2];
        HashMap<Float,Integer> map = new HashMap<>();
        for(int i=0; i<arr.length; i++) {
            map.put(arr[i],i);
            }

        for(int i=0; i<arr.length; i++){
            if(map.containsKey(target-arr[i])){
                results[0]=i;
                results[1]=map.get(target-arr[i]);
                return results;
            }

        }
        throw new NoSuchElementException("Brak rozwiazania!");

    }

    public static float [] createTable(){
        int n;
        System.out.println("Podaj wielkość tablicy");
        n = scanner.nextInt();
        float tab[] = new float[n];
        for(int i=0; i<tab.length;i++){
            tab[i]=algorithm.randFloat(0,50);
        }
        return tab;
    }

    public static float giveAnX(){
        float x;
        System.out.println("Podaj liczbę, do której mają być sumowane elementy tablicy");
        x=scanner.nextFloat();
        return x;
    }

    public static void showResults(int [] results){

        System.out.println("Szukane indeksy: ["+results[0]+"]"+","+"["+results[1]+"]");
    }

    public static void start(){
        showResults(algorithm.solution(createTable(),giveAnX()));


    }
}
