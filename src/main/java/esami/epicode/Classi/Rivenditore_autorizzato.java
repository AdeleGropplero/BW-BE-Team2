package esami.epicode.Classi;

public class Rivenditore_autorizzato extends PuntoDiAcquisto{
    private long id;

    public Rivenditore_autorizzato(boolean attivo) {
        super(attivo);
    }

    public Rivenditore_autorizzato() {
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
        return "Rivenditore_autorizzato{" +
                "id=" + id +
                "} " + super.toString();
    }
}
