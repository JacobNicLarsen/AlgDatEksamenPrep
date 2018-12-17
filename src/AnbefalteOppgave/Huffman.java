package AnbefalteOppgave;

import java.util.Comparator;

public class Huffman
{
    private static final class Node
    {
        private char tegn;     // et tegn
        private Node venstre;  // peker til venstre barn
        private Node høyre;    // peker til høyre barn

        private Node()         // konstruktør
        {
            tegn = 0; venstre = høyre = null;
        }
    } // slutt på class Node

    private Node rot;        // instansvariabel

    public Huffman(String[] bitkoder)
    {
        rot = new Node();

        for (int i = 0; i < bitkoder.length; i++) {
            if (bitkoder[i] != null) {
                Node p = rot;
                for (int j = 0; j < bitkoder[i].length(); j++) {
                    if (bitkoder[i].charAt(j) == 0){
                        if (p.venstre == null) p.venstre = new Node();
                        p = p.venstre;
                    }
                    else{
                        if (p.høyre == null){
                            p.høyre = new Node();
                            p = p.høyre;
                        }
                    }
                    p.tegn = (char) i;
                }
            }
        }
    }

} // slutt på class Huffman
