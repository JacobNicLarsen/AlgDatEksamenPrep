package eksamenv2016.hjelpeklasser;

public interface Kø<T>
{
    public boolean leggInn(T verdi);      // legger inn bakerst i køen
    public T kikk();                   // ser på den første i køen
    public T taUt();                   // tar ut den første i køen
    public int antall();               // antall i køen
    public boolean tom();              // er køen tom?
    public void nullstill();           // tømmer køen
    public String toString();          // fra den første til den siste
}
