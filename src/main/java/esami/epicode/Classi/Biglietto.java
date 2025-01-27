package esami.epicode.Classi;

import java.time.LocalDate;

public class Biglietto extends TitoloDiViaggio {
    private long id;
    private boolean utilizzabile;

    public Biglietto(LocalDate data_acquisto, PuntoDiAcquisto punto_di_acquisto) {
        super(data_acquisto, punto_di_acquisto);
        this.utilizzabile = true;
    }

    public Biglietto() {
    }

    public boolean isUtilizzabile() {
        return utilizzabile;
    }

    public void setUtilizzabile(boolean utilizzabile) {
        this.utilizzabile = utilizzabile;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Biglietto{" +
                "id=" + id +
                ", utilizzabile=" + utilizzabile +
                "} " + super.toString();
    }
}
