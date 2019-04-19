package Zad2;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Algorithm {

    public static List<Integer> randomList(int n) {

        Random rand = new Random();
        List<Integer> tmp = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int r = rand.nextInt(2100000)-1000000;
            tmp.add(r);
        }

        return tmp;
    }
    public static int solution(List<Integer> a){
        int result=0;
        int i;
        Set<Integer> set = new HashSet<>();
        int n = a.size();
        for (int x : a) {
            if (x > 0) {
                set.add(x);
            }
        }
        for ( i= 1; i <= n + 1; i++) {
            if (!set.contains(i)) {
                result= i;
                break;
            }
        }
        return result;
    }

    public static void start(){
        List<Integer> L = new ArrayList<>();
        int n;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj wielkość listy");
        n = scanner.nextInt();
        if(n>100000)
            throw new IllegalArgumentException("Ta wartość jest za duża");

        L=randomList(n);
        System.out.println("Najmniejsza liczba nieistniejąca w liście to: "+solution(L));
        System.out.println();
        Collections.sort(L);
        for(int x:L)
        {
            System.out.println(x);
        }
    }

}
