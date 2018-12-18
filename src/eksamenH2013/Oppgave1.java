package eksamenH2013;

public class Oppgave1 {

    public static int hash(String a, String b){

        StringBuilder s = new StringBuilder();
        int korteste = Math.min(a.length(), b.length());

        for (int i = 0; i < korteste; i++) {
            s.append(a.charAt(i));
            s.append(b.charAt(i));
        }
        s.append(a.substring(korteste));
        s.append(b.substring(korteste));

        return s.toString().hashCode();
    }

    public static boolean inneholdti(char[] a, char[] b){
        if (a.length > b.length) return false;
        int[]A = new int[25], B = new int[25];

        for (char c : a) A[c]++;
        for (char c : b) B[c]++;

        for (int i = 0; i < A.length; i++) {
            if (A[i] > B[i]) return false;
        }
        return true;
    }
}
