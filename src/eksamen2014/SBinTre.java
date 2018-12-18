package eksamen2014;

import java.util.Comparator;
import java.util.Objects;
import java.util.Stack;

public class SBinTre<T>
{
    private static final class Node<T> // en indre nodeklasse
    {
        private T verdi;                 // nodens verdi
        private Node<T> venstre, høyre;  // venstre og høyre barn
        private int høyde;               // nodens høyde

        private Node(T verdi)            // nodekonstruktør
        {
            this.verdi = verdi;
            venstre = null;
            høyre =   null;
            høyde = 0;
        }

    } // slutt på class Node

    private Node<T> rot;                       // peker til rotnoden
    private int antall;                        // antall noder
    private final Comparator<? super T> comp;  // komparator

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public int antall() { return antall; }

    public boolean tom() { return antall == 0; }

    public int høyde() { return tom() ? -1 : rot.høyde; }

    public boolean leggInn(T verdi)
    {
        if (tom()){
            rot = new Node<>(verdi);
            return true;
        }
        Stack<Node<T>> stakk = new Stack<>();
        Node<T> p = rot, q = null;
        int cmp = 0;
        while (p != null){
            stakk.add(p);
            cmp = comp.compare(verdi, p.verdi);
            p = cmp<0 ? p.venstre : p.høyre;
        }

        p = new Node<>(verdi);

        q = stakk.pop();
        if (cmp < 0) q.venstre = p;
        else q.høyre = p;

        while (q.høyde == p.høyde){
            q.høyde++;
            p = q;
            if (stakk.empty()) break;
            else q = stakk.pop();
        }
        antall++;
        return true;
    }

    public T nestMinst()
    {
        if (antall < 2) throw new IllegalArgumentException("Treet er mindre en 2");
        Node<T> p = rot, q = null;
        while (p.venstre != null){
            q = p;
            p = p.venstre;
        }

        if (p.høyre != null){
            p = p.høyre;
            while (p.venstre != null){
                p = p.venstre;
            }
            return p.verdi;
        }

        return q.verdi;
    }

    public T avstand(T verdi, int d)
    {
        Objects.requireNonNull(verdi, "Ingen null-verdier!");
        if (d < 0) throw new IllegalArgumentException("d kan ikke være negativ");
        Node<T> p = rot;

        while (p!= null){
            int cmp = comp.compare(verdi,p.verdi);

            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else break;
        }

        if (p == null) return null;

        if (p.høyre != null){
            Node<T> q = p.høyre;
            while (q.venstre != null) q = q.venstre;
            if (comp.compare(verdi, q.verdi) == 0) return null;
        }

        if (p.høyde < d) return null;

        while (d-- > 0){
            p = p.høyre != null && d <= p.høyre.høyde ? p.høyre : p.venstre;
        }

        return p.verdi;
    }
} // slutt på class SBinTre