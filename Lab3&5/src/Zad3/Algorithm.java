package Zad3;

import java.util.Scanner;

public class Algorithm {

    public static int substring(String a,String b){

        int q = 1;
        StringBuilder S = new StringBuilder(a);
        for (; S.length() < b.length(); q++) S.append(a);
        if (S.indexOf(b) >= 0) return q;
        if (S.append(a).indexOf(b) >= 0) return q+1;
        return -1;
    }

    public static void start(){
        String a,b;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj dwa napisy");
        a = scanner.nextLine();
        b=scanner.nextLine();
        System.out.println("Szukany wynik: "+substring(a,b));

    }

}
