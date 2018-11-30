package eksamen2017;

import java.util.Comparator;

public class SBinTre<T>
{
    private static final class Node<T> // en indre nodeklasse
    {
        private final T verdi;           // nodens verdi
        private Node<T> venstre, høyre;  // venstre og høyre barn

        private Node(T verdi)  // konstruktør
        {
            this.verdi = verdi;
            venstre = høyre = null;
        }

    } // class Node

    private Node<T> rot;
    private int antall;
    private int høyde;
    private final Comparator<? super T> comp;

    public SBinTre(Comparator<? super T> c)  // konstruktør - lager et tomt tre
    {
        rot = null;    // rot er null i et tomt tre
        antall = 0;    // et tomt tre har ingen noder
        høyde = -1;    // et tomt tre har høyde lik -1
        comp = c;      // komparatoren får verdi
    }

    public int antall() { return antall; }

    public boolean tom() { return antall == 0; }

    public int høyde() { return høyde; }

    public void leggInn(T verdi)
    {
        Node<T> p = rot, q = null;
        int cmp = 0;


        while (p != null)
        {
            q = p;
            cmp = comp.compare(verdi,p.verdi);
            p = cmp < 0 ? p.venstre : p.høyre;
        }

        p = new Node<>(verdi);

        if (q == null) rot = p;
        else if (cmp < 0) q.venstre = p;
        else q.høyre = p;

        antall++;
    }

    public T min()
    {

        if (tom()) throw new IllegalStateException("Treet er tomt");
        Node<T> p = rot;
        while (p != null) p = p.venstre;
        return p.verdi;
    }

    private int dybde(Node<T> p)
    {
        throw new IllegalStateException("Ikke laget");
    }

    public T[] nedersteNivå()
    {
        throw new IllegalArgumentException("Ikke laget");
    }

} // class SBinTre

