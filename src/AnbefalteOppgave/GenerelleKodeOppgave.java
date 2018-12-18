package AnbefalteOppgave;

import java.util.*;

public class GenerelleKodeOppgave {

    public static void main(String[] args) {
        int[] a = {2, 3, 5, 7, 10, 12, 12, 15, 18, 20};
        System.out.println( finn(a, 1) );   // utskrift -1
        System.out.println( finn(a, 12) );  // utskrift 5
        System.out.println( finn(a, 16) );  // utskrift -9
        System.out.println( finn(a, 21) );  // utskrift -11
        System.out.println( finn(a, 11) );  // utskrift -5

    }

    public static void bytt(int[] a, int l, int r){
        int temp = a[l];
        a[l] = a[r];
        a[r] = temp;
    }

    public static void snu(int[] a){
        int left = 0, right = a.length - 1;

        while (left<right){
            bytt(a,left,right);
            left++; right--;
        }
    }
    public static int finn(int[] a, int verdi){
        int v = 0, h = a.length - 1;  // v og h er intervallets endepunkter

        while (v < h)  // obs. må ha v < h her og ikke v <= h
        {
            int m = (v + h)/2;  // heltallsdivisjon - finner midten

            if (verdi > a[m]) v = m + 1;   // verdi må ligge i a[m+1:h]
            else  h = m;                   // verdi må ligge i a[v:m]
        }
        if (h < v || verdi < a[v]) return -(v + 1);  // ikke funnet
        else if (verdi == a[v]) return v;            // funnet
        else  return -(v + 2);                       // ikke funnet
    }




    public static <T> int indeks(Stack<T> s, T verdi){
        Stack<T> s2 = new Stack<>();
        int antall = s.size();
        int index = -1;

        for (int i = 0; i < antall; i++) {
            if (s.peek() == verdi){
                index = i;
                break;
            }
            s2.add(s.pop());
        }

        while (!s2.isEmpty()) s.add(s2.pop());
        return index;
    }

    public static String toString(int[] a){
        StringBuilder s = new StringBuilder();
        s.append("[");
        for (int i = 0; i < a.length; i++) {
            s.append(a[i]).append(",").append(" ");
        }
        s.append("]");
        return s.toString();
    }

    public static int[] snitt (int[] a, int[] b){
        int ut[] = new int[Math.min(a.length, b.length)];
        int j = 0, k = 0, antall = 0;

        while (j < a.length && k < b.length){
            if (a[j] < b[k]) j++;
            else if (a[j] == b[k]){
                ut[antall++] = a[j];
                j++;
                k++;
            }
            else k++;
        }

        return Arrays.copyOf(ut,antall);
    }

    public static <T> T maks(Queue<T> queue, Comparator<? super T> c){
        int size = queue.size();
        T maks = queue.peek();

        for (int i = 0; i < size; i++) {
            queue.add(queue.poll());
            if (c.compare(maks,queue.peek()) < 0) maks = queue.peek();
        }
        return maks;
    }

    public static <T> void kopier(Queue<T> A, Queue<T> B){
        int size = A.size();

        for (int i = 0; i < size; i++) {
            T temp = A.poll();
            A.add(temp);
            B.add(temp);
        }
    }

    public static void halvbytt(int[] a){

        if (a.length % 2 != 0) throw new IllegalStateException("Ikke partallslengde");
        int i = 0, j = a.length /2;

        while (i < a.length/2 && j <a.length){
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i++;
            j++;
        }
    }

    public static <T> void kopier(Stack<T> A, Stack<T> B){
        if (!B.isEmpty()) throw new IllegalArgumentException("Stack B er ikke tom");
        Stack<T> C = new Stack<>();

        while (!A.isEmpty())C.add(A.pop());

        while (!C.isEmpty()){
            T temp = C.pop();
            A.add(temp);
            B.add(temp);
        }
    }

    public static <T> int fjernBakerst(Queue<T> kø, int antall){
        for (int i = 0; i < kø.size() - antall; i++) {
            kø.add(kø.poll());
        }

        int antallFjernet = 0;
        while (!kø.isEmpty() && antallFjernet < antall){
            kø.poll();
            antallFjernet++;
        }

        return antallFjernet;
    }



}
