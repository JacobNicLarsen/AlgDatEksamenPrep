package eksamenv2015;

import java.util.Arrays;
import java.util.StringJoiner;

public class Mengde
{
    private int[] a;

    public Mengde()  // konstruktør
    {
        a = new int[0];
    }

    public Mengde(int[] b, int n)  // konstruktør
    {
        if (n < 0 || n >b.length){
            throw new IllegalArgumentException("N er utenfor talbellen");
        }

        for (int i = 1; i < n; i++) {
            if (b[i-1] >= b[i]){
                String melding = b[i-1] > b[i] ? "Usortert" : "Duplikat";
                throw new IllegalArgumentException(melding);
            }
        }
        a = Arrays.copyOf(b,n);
    }

    public String toString()
    {
        return Arrays.toString(a);
    }

    public boolean equals(Mengde B)
    {
        // kode mangler - skal kodes
        if (this == B) return true;
        return Arrays.equals(a,B.a);
    }

    public Mengde snitt(Mengde B)
    {
        int[] b = B.a;
        int[] c = new int[Math.min(a.length, b.length)];  // hjelpetabell

        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length)
        {
            if (a[i] < b[j]) i++;      // hopper over a[i]
            else if (a[i] == b[j])
            {
                c[k++] = a[i++];         // a[i] == b[j], tar med a[i]
                j++;                     // hopper over b[j]
            }
            else  j++;                 // hopper over b[j]
        }

        return new Mengde(c,k);      // bruker konstruktøren
    }

    public static void main(String[] args) {
        int[] a = {2,3,4,5,7,9,11,15};
        int[] b = {1,2,5,9,11,13};
        int[] c = {3,4,6,10,12};

        Mengde A = new Mengde(a, a.length);  // hele a
        Mengde B = new Mengde(b, b.length);  // hele b
        Mengde C = new Mengde(c, c.length);  // hele c

        System.out.println(A.snitt(B) + "  " + B.snitt(C));
        // Utskrift: [2, 5, 9, 11]  []
    }

} // class Mengde


