package eksamenH2013;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Queue;

public class SBinTre<T>
{
    private static final class Node<T> // en indre nodeklasse
    {
        private T verdi;                 // nodens verdi
        private Node<T> venstre, høyre;  // venstre og høyre barn
        private Node<T> forelder;        // nodens forelder

        private Node(T verdi, Node<T> v, Node<T> h, Node<T> f)
        {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            forelder = f;
        }

        private Node(T verdi)
        {
            this(verdi, null, null, null);
        }

    } // class Node

    private Node<T> rot;
    private int antall;
    private final Comparator<? super T> comp;

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null; antall = 0; comp = c;
    }

    public int antall()
    {
        return antall;
    }

    public boolean tom()
    {
        return antall == 0;
    }

    public boolean leggInn(T verdi)
    {
        Node<T> p = rot, q = null;
        int cmp = 0;

        while (p != null)
        {
            q = p;
            cmp = comp.compare(verdi,p.verdi);
            p = cmp < 0 ? p.venstre : p.høyre;
        }

        p = new Node<>(verdi, null, null, q);

        if (q == null) rot = p;
        else if (cmp < 0) q.venstre = p;
        else q.høyre = p;

        antall++;
        return true;
    }

    private static <T> int nivå(Node<T> p)
    {
        int nivå = -1;
        while (p != null){
            nivå++;
            p = p.forelder;
        }
        return nivå;
    }

    private static <T> Node<T> nestePreorden(Node<T> p)
    {
        if (p.venstre != null) return p.venstre;
        else if (p.høyre != null) return p.høyre;
        else {
            Node<T> f = p.forelder;
            while (f != null && (f.høyre == p) || f.høyre == null){
                p = f; f = f.forelder;
            }
            return f == null ? null : f.høyre;
        }
    }

    public int bredde()
    {
        Queue<Node<T>> kø = new ArrayDeque<>();
        if (!tom()) kø.add(rot);

        int bredde = 0;

        while (!kø.isEmpty()){
            int pånivå = kø.size();
            if (pånivå > bredde) bredde = pånivå;

            for (int i = 0; i < pånivå; i++) {
                Node<T> q = kø.poll();
                if (q.venstre != null) kø.add(q.venstre);
                if (q.høyre != null) kø.add(q.høyre);
            }
        }
        return bredde;
    }
} // SBinTre
