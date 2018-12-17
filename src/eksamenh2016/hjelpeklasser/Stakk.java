package eksamenh2016.hjelpeklasser;

public interface Stakk<T>
{
    public void leggInn(T verdi);      // legger verdi på toppen
    public T kikk();                   // ser på den øverste
    public T taUt();                   // tar ut den øverste
    public int antall();               // antall på stakken
    public boolean tom();              // er stakken tom?
    public void nullstill();           // tømmer stakken
    public String toString();          // fra toppen til bunnen
}
