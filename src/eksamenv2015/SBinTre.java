package eksamenv2015;

import java.util.*;

public class SBinTre<T>
{
    private static final class Node<T> // en indre nodeklasse
    {
        private T verdi;                 // nodens verdi
        private Node<T> venstre, høyre;  // venstre og høyre barn
        private int vAntall;             // antall noder i venstre subtre

        private Node(T verdi)  // konstruktør
        {
            this.verdi = verdi;
            venstre = høyre = null;
            vAntall = 0;
        }
    } // class Node

    private Node<T> rot;
    private int antall;
    private final Comparator<? super T> comp;

    public SBinTre(Comparator<? super T> c)  // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public int antall(){ return antall; }

    public boolean tom(){ return antall == 0; }

    public boolean leggInn(T verdi)
    {
        Node<T> p = rot, q = null;
        int cmp = 0;

        while (p != null)
        {
            q = p;
            cmp = comp.compare(verdi,p.verdi);
            if (cmp < 0){
                p.vAntall++;
                p = p.venstre;
            }
            else p = p.høyre;
        }

        p = new Node<>(verdi);

        if (q == null) rot = p;
        else if (cmp < 0) q.venstre = p;
        else q.høyre = p;

        antall++;
        return true;
    }

    public T minst()
    {
        if (tom())throw new NoSuchElementException("Treet er tomt!");
        Node<T> p = rot;
        while (p != null){
            p = p.venstre;
        }

        return p.verdi;
    }

    public T hent(int indeks) {
        List<Node<T>> s = new LinkedList<>();
        hent(s,rot,indeks);

        return s.get(indeks).verdi;
    }

    public static <T> void hent(List<Node<T>> s, Node<T>p, int indeks){
        if (s.size() <= indeks){
            if (p.venstre != null) hent(s,p.venstre,indeks);
            s.add(p);
            if (p.høyre != null) hent(s,p.høyre,indeks);
        }
    }

    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append("[");
        inoden(s,rot);
        s.append("]");

        return s.toString();
    }

    private void inoden(StringBuilder s, Node<T> p){
        if (p.venstre != null) inoden(s, p.venstre);
        s.append(p.verdi).append(", ");
        if (p.høyre != null) inoden(s,p.høyre);
    }

    public boolean inneholder (T verdi){
        Node<T> p = rot;
        while (p != null){
            if (comp.compare(p.verdi, verdi) < 0) p = p.venstre;
            else if (comp.compare(p.verdi, verdi)>0) p = p.høyre;
            else return true;
        }
        return false;
    }

    public String kortsteGren(){
        if (tom()) return "[]";

        Queue<Node<T>> kø = new ArrayDeque<>();
        Node<T> p = rot;
        kø.add(p);

        while (true){
            p = kø.poll();
            if (p.venstre == null && p.høyre == null) break;
            if (p.venstre != null) kø.add(p.venstre);
            if (p.høyre != null) kø.add(p.høyre);
        }
        return gren(p.verdi);
    }

    public String lengstreGren(){
        Queue<Node<T>> kø = new ArrayDeque<>();
        Node<T> p = rot;
        kø.add(p);

        while (!kø.isEmpty()){
            p = kø.poll();
            if (p.venstre != null) kø.add(p.venstre);
            if (p.høyre != null) kø.add(p.høyre);
        }
        return gren(p.verdi);
    }


    private String gren(T bladnodeverdi){
        Node<T> p = rot;
        StringJoiner s = new StringJoiner("[","]", ", ");

        while (p!= null){
            s.add(p.verdi.toString());
            p = comp.compare(bladnodeverdi, p.verdi) < 0 ? p.venstre : p.høyre;
        }
        return s.toString();
    }

    public T sistePreoden(){
        Node<T> p = rot;

        while (true){
            if (p.høyre != null) p = p.høyre;
            else if (p.venstre != null) p = p.venstre;
            else return p.verdi;
        }
    }


    public static void main(String[] args) {
        Comparator<Integer> c = Comparator.naturalOrder(); // den vanlige ordningen

        SBinTre<Integer> tre = new SBinTre<>(c);

        int[] a = {8,2,1,4,3,6,5,7,11,9,10,15,13,12,14};

        for (int k : a) tre.leggInn(k);

        System.out.println(tre);  // toString kalles implisitt

        // Utskrift: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]
    }
} // SBinTre
