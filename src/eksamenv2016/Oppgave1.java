package eksamenv2016;

import eksamenv2016.hjelpeklasser.*;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class Oppgave1 {
    public static void main(String[] args) {
        Kø<String> A = new TabellKø<>();      // oppretter kø A
        Kø<String> B = new TabellKø<>();      // oppretter kø B

        String[] navn1 = {"Per","Kari","Elin","Ali","Jens"};
        String[] navn2 = {"Åse","Ole","Kjersti"};

        for (String n : navn1) A.leggInn(n);  // legger inn i kø A
        for (String n : navn2) B.leggInn(n);  // legger inn i kø B

        System.out.println(A + "  " + B);     // skriver ut A og B
        flytt(A,B);                           // B flyttes over i A
        System.out.println(A + "  " + B);     // skriver ut A og B

        // Utskrift: [Per, Kari, Elin, Ali, Jens]  [Åse, Ole, Kjersti]
               //[Per, Åse, Kari, Ole, Elin, Kjersti, Ali, Jens]  []
    }

    public static <T> int indeks(Stakk<T> s, T verdi){
        Stakk<T> stakk2 = new LenketStakk<>();
        int utverdi = -1;
        int antall = s.antall();
        for (int i = 0; i < antall; i++) {
            if (s.kikk().equals(verdi)){
                utverdi = i;
                break;
            }
            stakk2.leggInn(s.taUt());
        }
        while (!stakk2.tom()) s.leggInn(stakk2.taUt());

        return utverdi;
    }

    public static <T> void flytt(Kø<T> A, Kø<T> B)
    {
        int n = A.antall();
        int m = Integer.min(n, B.antall());

        for (int i = 0; i < m; i++)
        {
            A.leggInn(A.taUt());   // en fra A og
            A.leggInn(B.taUt());   // så en fra B
        }

        // må ta med det som måtte være igjen i A eller B
        for (int i = m; i < n; i++) A.leggInn(A.taUt());
        while (!B.tom()) A.leggInn(B.taUt());
    }
}