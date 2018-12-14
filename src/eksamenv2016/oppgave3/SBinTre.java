package eksamenv2016.oppgave3;

import eksamenv2016.hjelpeklasser.LenketStakk;
import eksamenv2016.hjelpeklasser.Stakk;
import eksamenv2016.hjelpeklasser.TabellStakk;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SBinTre<T> implements Iterable<T>
{
    private static final class Node<T> // en indre nodeklasse
    {
        private T verdi;                 // nodens verdi
        private Node<T> venstre, høyre;  // venstre og høyre barn

        private Node(T verdi, Node<T> v, Node<T> h)  // konstruktør
        {
            this.verdi = verdi;
            venstre = v; høyre = h;
        }

        private Node(T verdi)  // konstruktør
        {
            this(verdi, null, null);
        }
    } // class Node

    private Node<T> rot;                       // peker til rotnoden
    private int antall;                        // antall noder
    private final Comparator<? super T> comp;  // komparator

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public Comparator<? super T> comparator()
    {
        return comp;             // returnerer treets komparator
    }

    public int antall()
    {
        return antall;           // returnerer antall verdier i treet
    }

    public boolean tom()
    {
        return antall == 0;      // sjekker om treet er tomt
    }

    public boolean leggInn(T verdi)
    {
        Objects.requireNonNull(verdi, "Ikke tillatt med null-verdier!");

        Node<T> p = rot, q = null;
        int cmp = 0;

        while (p != null)
        {
            q = p;  // q blir forelder til p
            cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return false;
        }

        p = new Node<>(verdi);

        if (tom()) rot = p;
        else if (cmp < 0) q.venstre = p;
        else q.høyre = p;

        antall++;
        return true;  // vellykket innlegging
    }

    private int dybde(T verdi, Node<T> p)
    {
        int dybde = -1;

        while (p != null)
        {
            dybde++;
            int cmp = comp.compare(verdi, p.verdi);

            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return dybde;
        }
        return -1;
    }

    public int dybde(T verdi)
    {
        return dybde(verdi, rot);
    }

    public int avstand(T verdi1, T verdi2)
    {
        if (comp.compare(verdi1,verdi2) < 0){ // Setter verdi 1 til laveste verdien av 1 og 2
            T temp = verdi1;
            verdi1 = verdi2;
            verdi2 = temp;
        }

        Node<T> p = rot;

        while (p != null){
            if (comp.compare(verdi2, verdi1) < 0)
                p = p.venstre;
            else if (comp.compare(verdi1, p.verdi) > 0)
                p = p.høyre;
            else break;
        }

        if (p == null)
            throw new IllegalArgumentException("Verdi ikke i treet");
        int dybde1 = dybde(verdi1,p), dybde2 = dybde(verdi2,p);

        if (dybde1 == -1 || dybde2 == -1)
            throw new IllegalArgumentException("Verdien ligger ikke i treet");

        return dybde1 + dybde2;
    }

    public int diameter()
    {
        // skal kodes
        throw new IllegalArgumentException("Ikke laget enda");
    }


    private class InordenIterator implements Iterator<T>
    {
        private Stakk<Node<T>> stakk = new TabellStakk<>();
        private Node<T> p = null;

        private Node<T> først(Node<T> q)
        {
            while (q.venstre != null)
            {
                stakk.leggInn(q);
                q = q.venstre;
            }
            return q;
        }

        private InordenIterator()
        {
            if (!tom()) p = først(rot);
        }

        public boolean hasNext()
        {
            return p != null;
        }

        public T next()
        {
            if (!hasNext()) throw new NoSuchElementException();

            T verdi = p.verdi;

            if (p.høyre != null) p = først(p.høyre);
            else if (!stakk.tom()) p = stakk.taUt();
            else p = null;

            return verdi;
        }

    } // InordenIterator

    public Iterator<T> iterator()
    {
        return new InordenIterator();
    }

} // class SBinTre
