package eksamenh2016;

import eksamenh2016.hjelpeklasser.*;
import eksamenh2016.oppgave3.SBinTre;

import java.util.Comparator;

public class Oppgave1 {
    public static void main(String[] args) {
        SBinTre sBinTre = new SBinTre(Comparator.naturalOrder());
        int[] array = { 7, 4, 18, 1, 6, 14, 30, 12, 15, 9, 25, 22, 27, 10, 20};

        for (int ettall : array){
            sBinTre.leggInn(ettall);
        }
        System.out.println(sBinTre.dybde(10));
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