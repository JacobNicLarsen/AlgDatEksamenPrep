package eksamen2016;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        /* Oppgave 1
        char[] c = "AbaAcBbAAaCCbcAB".toCharArray();
        int antall = omorganiser(c);
        System.out.println(antall + "  " + Arrays.toString(c));
        // Utskrift: 7  [c, b, a, b, c, a, b, A, A, B, C, C, A, A, A, B]*/

        /* Oppgave 2
        Character[] tegn1 = {'A','B','C'}, tegn2 = {'A','B','D'};
        Integer[] tall1 = {1,2,3,4,5}, tall2 = {1,2,3,4};

        Liste<Character> a = new DobbeltLenketListe<>(tegn1);  // A,B,C
        Liste<Character> b = new DobbeltLenketListe<>(tegn2);  // A,B,D

        Liste<Integer> c = new DobbeltLenketListe<>(tall1);    // 1,2,3,4,5
        Liste<Integer> d = new DobbeltLenketListe<>(tall2);    // 1,2,3,4

        int cmp1 = compareList(a, b, Comparator.naturalOrder());  // cmp1 skal bli negativ
        int cmp2 = compareList(c, d, Comparator.naturalOrder());  // cmp2 skal bli positiv
        System.out.println("Cmp 1: " + cmp1 + "\n" +  "Cmp2 " + cmp2);*/

        Stack<String> a = new java.util.Stack<>(), b = new java.util.Stack<>();
        a.add("C"); a.add("B"); a.add("A");
        System.out.println(a + " " + b);  // utskrift: [A, B, C] []
        omvendtkopi(a,b);
        System.out.println(a + " " + b);  // utskrift: [A, B, C] [C, B, A]

    }

    //Oppgave 1A.
    public static int omorganiser(char[] array){
        int left = 0, right = array.length - 1;

        while (left < right){
            if (Character.isLowerCase(array[left])) left++;
            if (Character.isUpperCase(array[right])) right--;
            char temp = array[left];
            array[left] = array[right];
            array[right] = temp;
        }
        return left;
    }

    public static <T> int compareList(Liste<T> a, Liste<T> b, Comparator<? super T> comp){
        int length = Math.min(a.antall(),b.antall());

        for (int i = 0; i < length; i++) {
            int compare = comp.compare(a.hent(i), b.hent(i));
            if (compare != 0) return compare;
        }

        return a.antall() - b.antall();
    }

    public static <T> void omvendtkopi(Stack<T> a, Stack<T> b){
        Stack<T> c = new Stack<>();

        while (!a.empty()){
            T temp = a.pop();
            b.add(temp);
            c.add(temp);
        }
        while (!c.empty()) a.add(c.pop());
    }
}
