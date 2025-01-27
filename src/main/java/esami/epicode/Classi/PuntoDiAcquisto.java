package esami.epicode.Classi;

public abstract class PuntoDiAcquisto {
    private long id;
    private boolean attivo;

    public PuntoDiAcquisto(boolean attivo) {
        this.attivo = attivo;
    }

    public PuntoDiAcquisto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isAttivo() {
        return attivo;
    }

    public void setAttivo(boolean attivo) {
        this.attivo = attivo;
    }

    @Override
    public String toString() {
        return "Punto_di_acquisto{" +
                "id=" + id +
                ", attivo=" + attivo +
                '}';
    }
}
