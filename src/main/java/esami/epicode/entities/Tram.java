package esami.epicode.entities;

import esami.epicode.Entity.Biglietto;

import javax.persistence.Entity;
import java.time.LocalDate;


@Entity
public class Tram extends Veicolo {

    private static int capienza= 40;

    public Tram (){

    }

    public Tram(boolean inServizio) {
        super(inServizio);

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
