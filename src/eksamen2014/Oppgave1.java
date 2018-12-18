package eksamen2014;

import java.util.LinkedList;
import java.util.Queue;

public class Oppgave1 {
    public static void main(String[] args) {
        String[] person = {"Per","Kari","Elin","Ali","Jens","Siri"};
        Queue<String> kø = new LinkedList<>();
        for (String p : person) ((LinkedList<String>) kø).add(p);  // legger inn i køen

        System.out.println(kø);  // [Per, Kari, Elin, Ali, Jens, Siri]
        byttPlass(kø, 4);  // den på indeks 4 bytter plass med den rett bak (indeks 5)
        System.out.println(kø);  // [Per, Kari, Elin, Ali, Siri, Jens]
    }

    public static <T> void byttPlass(Queue<T> kø, int indeks){
        int køLengde = kø.size();
        for (int i = 0; i < indeks; i++) {
            kø.add(kø.poll());
        }
        T temp = kø.poll();
        kø.add(kø.poll());
        kø.add(temp);

        for (int i = 0; i < køLengde - indeks - 2; i++) {
            kø.add(kø.poll());
        }
    }
}
