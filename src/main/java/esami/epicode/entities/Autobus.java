package esami.epicode.entities;

import esami.epicode.Entity.Biglietto;

import javax.persistence.Entity;
import java.time.LocalDate;


@Entity
public class Autobus extends Veicolo {


    private static int capienza= 60;


    public Autobus() {

    }

    public Autobus(boolean inServizio) {
        super(inServizio);

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
                "id=" + getCodiceVeicolo() +
                '}';
    }

    @Override
    public Biglietto obliteraBiglietto(Biglietto b) {
        this.setNumBigliettiVidimati(this.getNumBigliettiVidimati()+1);
        b.setUtilizzabile(LocalDate.now());
        return b;
    }


}
