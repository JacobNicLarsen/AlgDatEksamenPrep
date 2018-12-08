package Oblig1;

import java.util.Arrays;
import java.util.Random;

public class Oblig1 {
    public static void main(String[] args) {
        int a[] = {1,2,54,1,123,5,43,1,234,5,61};

        System.out.println(ombyttinger(a));
    }



    public static void bytt(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static int maks(int[] a){
        if (a.length == 0) throw new IllegalArgumentException("Kan ikke ha en tom array");

        for (int i = 1; i < a.length; i++) {
            if (a[i - 1] > a[i]){
                bytt(a,i,i - 1);
            }
        }
        return a[a.length-1];
    }

    public static int ombyttinger(int[]  a){
        if (a.length == 0) throw new IllegalArgumentException("Kan ikke ha en tom array");

        int ombyttinger = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1] > a[i]){
                bytt(a,i,i - 1);
                ombyttinger++;
            }
        }
        return ombyttinger;
    }
}
