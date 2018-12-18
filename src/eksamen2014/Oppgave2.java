package eksamen2014;

import java.util.Arrays;

public class Oppgave2 {

    public static void main(String[] args) {  int[] a = {1,3,5,5,6,8,8,8,9,10,10};    // en sortert tabell med duplikater
        int antall = fjernDuplikater(a);        // kaller metoden
        System.out.println(antall + ": " + Arrays.toString(a));  // antallet og tabellen
        // Utskrift: 7: [1, 3, 5, 6, 8, 9, 10, 0, 0, 0, 0]

    }

    public static int fjernDuplikater(int[] a){
        if (a.length == 0) return 0;
        int[] a2 = new int[a.length];
        int tall = a[0];
        a2[0] = tall;
        int teller = 1;
        for (int i = 1; i < a.length; i++) {
            if (tall < a[i]){
                a2[teller++] = a[i];
                tall = a[i];
            }
        }
        System.arraycopy(a2,0,a,0,a.length);
        return teller;
    }
}
