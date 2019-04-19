package Zad5;


import java.util.Random;
import java.util.Scanner;

public class TimeMeasurement {

    public static void start(){
        int c;
        Scanner scanner = new Scanner(System.in);
        Random gen = new Random();
        int n = 10000;
        int optimistic[] = new int[n];
        int pesimistic[] = new int[n];
        int realistic[] = new int[n];

        for(int i=0;i<n;i++){
            optimistic[i]=i;
            pesimistic[i]=n-i;
            realistic[i]=gen.nextInt(n);
        }

        BubbleSort bubbleSort = new BubbleSort();
        ShellSort shellSort = new ShellSort();
        InsertionSort insertionSort = new InsertionSort();
        SelectionSort selectionSort = new SelectionSort();
        CombSort combSort = new CombSort();

        int realTemp[] = new int[n];
        for(int i=0;i<n;i++) {
            realTemp[i]= realistic[i];
        }

        int licznik =0;

        while  (licznik<5)  {
            if(licznik ==0){
                System.out.println("BubbleSort:");
                long tStart = System.currentTimeMillis();
                bubbleSort.bubbleSort(optimistic);
                long tEnd = System.currentTimeMillis();
                long tDelta = tEnd - tStart;
                double elapsedSeconds = tDelta / 1000.0;
                System.out.println("Optimistic time: "+elapsedSeconds);

                tStart = System.currentTimeMillis();
                bubbleSort.bubbleSort(pesimistic);
                tEnd = System.currentTimeMillis();
                tDelta = tEnd - tStart;
                elapsedSeconds = tDelta / 1000.0;
                System.out.println("Pesimistic time: "+elapsedSeconds);

                tStart = System.currentTimeMillis();
                bubbleSort.bubbleSort(realistic);
                tEnd = System.currentTimeMillis();
                tDelta = tEnd - tStart;
                elapsedSeconds = tDelta / 1000.0;
                System.out.println("Realistic time: "+elapsedSeconds);
            }
            else if(licznik ==1){
                System.out.println("InsertionSort");
                long tStart = System.currentTimeMillis();
                insertionSort.sort(optimistic);
                long tEnd = System.currentTimeMillis();
                long tDelta = tEnd - tStart;
                double elapsedSeconds = tDelta / 1000.0;
                System.out.println("Optimistic time: "+elapsedSeconds);

                tStart = System.currentTimeMillis();
                insertionSort.sort(pesimistic);
                tEnd = System.currentTimeMillis();
                tDelta = tEnd - tStart;
                elapsedSeconds = tDelta / 1000.0;
                System.out.println("Pesimistic time: "+elapsedSeconds);

                tStart = System.currentTimeMillis();
                insertionSort.sort(realistic);
                tEnd = System.currentTimeMillis();
                tDelta = tEnd - tStart;
                elapsedSeconds = tDelta / 1000.0;
                System.out.println("Realistic time: "+elapsedSeconds);
            }
            else if(licznik ==2){

                System.out.println("Shell Sort:");
                long tStart = System.currentTimeMillis();
                shellSort.sort(optimistic);
                long tEnd = System.currentTimeMillis();
                long tDelta = tEnd - tStart;
                double elapsedSeconds = tDelta / 1000.0;
                System.out.println("Optimistic time: "+elapsedSeconds);

                tStart = System.currentTimeMillis();
                shellSort.sort(pesimistic);
                tEnd = System.currentTimeMillis();
                tDelta = tEnd - tStart;
                elapsedSeconds = tDelta / 1000.0;
                System.out.println("Pesimistic time: "+elapsedSeconds);

                tStart = System.currentTimeMillis();
                shellSort.sort(realistic);
                tEnd = System.currentTimeMillis();
                tDelta = tEnd - tStart;
                elapsedSeconds = tDelta / 1000.0;
                System.out.println("Realistic time: "+elapsedSeconds);

            }
            else if(licznik ==3){

                System.out.println("CombSort");
                long tStart = System.currentTimeMillis();
                combSort.sort(optimistic);
                long tEnd = System.currentTimeMillis();
                long tDelta = tEnd - tStart;
                double elapsedSeconds = tDelta / 1000.0;
                System.out.println("Optimistic time: "+elapsedSeconds);

                tStart = System.currentTimeMillis();
                combSort.sort(pesimistic);
                tEnd = System.currentTimeMillis();
                tDelta = tEnd - tStart;
                elapsedSeconds = tDelta / 1000.0;
                System.out.println("Pesimistic time: "+elapsedSeconds);

                tStart = System.currentTimeMillis();
                combSort.sort(realistic);
                tEnd = System.currentTimeMillis();
                tDelta = tEnd - tStart;
                elapsedSeconds = tDelta / 1000.0;
                System.out.println("Realistic time: "+elapsedSeconds);

            }
            else if(licznik ==4){
                System.out.println("Selection Sort:");
                long tStart = System.currentTimeMillis();
                selectionSort.sort(optimistic);
                long tEnd = System.currentTimeMillis();
                long tDelta = tEnd - tStart;
                double elapsedSeconds = tDelta / 1000.0;
                System.out.println("Optimistic time: "+elapsedSeconds);

                tStart = System.currentTimeMillis();
                selectionSort.sort(pesimistic);
                tEnd = System.currentTimeMillis();
                tDelta = tEnd - tStart;
                elapsedSeconds = tDelta / 1000.0;
                System.out.println("Pesimistic time: "+elapsedSeconds);

                tStart = System.currentTimeMillis();
                selectionSort.sort(realistic);
                tEnd = System.currentTimeMillis();
                tDelta = tEnd - tStart;
                elapsedSeconds = tDelta / 1000.0;
                System.out.println("Realistic time: "+elapsedSeconds);
            }

            for(int i=0;i<n;i++) {
                pesimistic[i] = n - i;
                realistic[i] = realTemp[i];
            }
            licznik++;
            System.out.println();

        }

    }
}
