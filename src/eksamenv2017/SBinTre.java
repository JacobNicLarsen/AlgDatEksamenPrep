package eksamenv2017;

import java.util.*;

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
        int nyHøyde = 0;

        while (p != null)
        {
            q = p;
            cmp = comp.compare(verdi,p.verdi);
            p = cmp < 0 ? p.venstre : p.høyre;
            nyHøyde++;
        }

        p = new Node<>(verdi);

        if (q == null) rot = p;
        else if (cmp < 0) q.venstre = p;
        else q.høyre = p;

        if (nyHøyde < høyde) høyde = nyHøyde;
        antall++;
    }

    public T min()
    {
        if (tom()) return null;

        Node<T> p = rot;

        while (p.venstre != null) p = p.venstre;

        return p.verdi;
    }

    private int dybde(Node<T> p)
    {
        Node<T> q = rot;
        int dybde = 1;
        while (q != p){
            int cmp = comp.compare(q.verdi, p.verdi);
            dybde++;
            q = cmp < 0 ? p.venstre : p.høyre;
        }

        return dybde;
    }

    @SuppressWarnings("unchecked")
    public T[] nedersteNivå()
    {
        ArrayList<T> l = new ArrayList<>();
        nedersteNivå(rot,l,0,høyde);
        return (T[])l.toArray();
    }

    private static <T> void nedersteNivå (Node<T> p, List<T> l, int nivå, int høyde){
        if (nivå == høyde) l.add(p.verdi);
        if (p.venstre != null) nedersteNivå(p.venstre, l, nivå, høyde);
        if (p.høyre != null) nedersteNivå(p.høyre, l, nivå, høyde);
    }

    public T[] nedersteNivåKø(){
        if (tom()) return (T[]) new Object();

        int nivå = 0, høyde = høyde(), nivåAntall = 0;
        Queue<Node<T>> kø = new LinkedList<>();

        kø.add(rot);

        while (!kø.isEmpty()){
            nivåAntall = kø.size();

            if (nivå == høyde) break;

            for (int i = 0; i < nivå; i++) {
                Node<T> p = kø.poll();
                if (p.venstre!= null) kø.add(p.venstre);
                if (p.høyre != null) kø.add(p.høyre);
            }
            nivå++;
        }

        T[] a = (T[]) new Object[nivåAntall];

        int i = 0;
        while (!kø.isEmpty()) a[i++] = kø.poll().verdi;

        return a;
    }




} // class SBinTre
