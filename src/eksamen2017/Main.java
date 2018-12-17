package eksamen2017;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {

        for (int i = 4; i < 50; i++) {
            for (int j = 2; j < i; j++) {
                if (i % j ==0){
                    i++;
                    break;
                }
            }
            System.out.println(i);
        }


        /*
        int[] a = {2, 3, 5, 7, 10, 12, 12, 15, 18, 20};
        System.out.println( finn(a, 1) );   // utskrift -1
        System.out.println( finn(a, 12) );  // utskrift 5
        System.out.println( finn(a, 16) );  // utskrift -9
        System.out.println( finn(a, 21) );  // utskrift -11*/
    }


    // Oppgave 1A.
    public static void snu(int[] a){
        int v = 0, h = a.length - 1; // Peker på hver side av tabellen

        while (v < h){ // så lenge venste er mindre enn høyre bytter de verdier
            int temp = a[v];
            a[v] = a[h];
            a[h] = temp;
            v++;
            h--;
        }
    }

    //Oppgave 1B.
    public static int finn(int[] a, int verdi){
        return binærsøk(a,0,a.length,verdi);
    }

    public static int binærsøk(int[] a, int fra, int til, int verdi){
        int v = fra, h = til - 1;

        while (h>v){
            int m = (h+v)/2;

            if (verdi > a[m]) v = m + 1;
            else h = m;
        }

        if (v < h || verdi < a[v]) return -(v + 1);
        else if (verdi == a[v]) return v;
        else return -(v + 2);
    }

    public static <T> int fjernBakerst(Queue<T> kø, int antall){
        if (antall < 0) throw new IllegalArgumentException("Antall må være positivt");
        int n = kø.size();

        if (antall >= kø.size()){
            kø.clear();
            return n;
        }

        for (int i = 0; i < n - antall; i++) kø.add(kø.remove());
        for (int i = 0; i < antall; i++) kø.remove();
        return antall;
    }
}