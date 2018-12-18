package eksamenv2017;

import java.util.LinkedList;
import java.util.Queue;

public class Oppgave3 {

    public static void main(String[] args) {
        Queue<Character> kø = new LinkedList<>();
        char[] c = "ABCDEFGHI".toCharArray();
        for (char d : c) kø.add(d);
        System.out.println(kø);                  // [A, B, C, D, E, F, G, H, I]
        int antall = fjernBakerst(kø,5);
        System.out.println(antall + " " + kø);   // 5 [A, B, C, D]
        antall = fjernBakerst(kø,5);
        System.out.println(antall + " " + kø);   // 4 []
    }

        public static <T> int fjernBakerst(Queue<T> kø, int antall){
        int køStørrelse = kø.size();
            for (int i = 0; i < køStørrelse - antall; i++) {
                kø.add(kø.poll());
            }

            int antallFjernet = 0;

            for (int i = 0; i < antall; i++) {
                if (!kø.isEmpty()){
                    kø.poll();
                    antallFjernet ++;
                }
            }
            return antallFjernet;
        }


}
