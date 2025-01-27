package esami.epicode.entities;

import esami.epicode.Entity.Biglietto;

import javax.persistence.Entity;


@Entity
public class Tram extends Veicolo {

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


    @Override
    public String toString() {
        return "Tram{" +
                "id=" +
                '}';
    }

    @Override
    public Biglietto obliteraBiglietto(Biglietto b) {
        this.setNumBigliettiVidimati(this.getNumBigliettiVidimati()+1);
        b.setUtilizzabile(false);
        return b;
    }





}
