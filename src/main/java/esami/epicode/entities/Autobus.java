package esami.epicode.entities;

public class Autobus extends Veicolo {

    private long id;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Autobus{" +
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
