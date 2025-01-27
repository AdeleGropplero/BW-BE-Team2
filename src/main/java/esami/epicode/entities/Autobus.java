package esami.epicode.entities;

import javax.persistence.Entity;


@Entity
public class Autobus extends Veicolo {


    private static int capienza= 60;


    public Autobus() {

    }

    public Autobus(boolean inServizio, Tratta tratta) {
        super(inServizio, tratta);

    }

    public static int getCapienza() {
        return capienza;
    }

    public static void setCapienza(int capienza) {
        Autobus.capienza = capienza;
    }





    @Override
    public String toString() {
        return "Autobus{" +
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
