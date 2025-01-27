package esami.epicode.entities;

import java.util.List;

public class Tram extends Veicolo {

    private long id;
    private static int capienza= 40;

    public Tram (){

    }

    public Tram(boolean inServizio, Tratta tratta) {
        super(inServizio, tratta);

    }

    public static int getCapienza() {
        return capienza;
    }

    public static void setCapienza(int capienza) {
        Tram.capienza = capienza;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Tram{" +
                "id=" + id +
                '}';
    }

    @Override
    public Biglietto obliteraBiglietto(Biglietto b) {
        this.setNumBigliettiVidimati(this.getNumBigliettiVidimati()+1);
        b.setUtilizzabile(false);
        return b;
    }




}
