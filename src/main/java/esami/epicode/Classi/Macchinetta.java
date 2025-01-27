package esami.epicode.Classi;

public class Macchinetta extends PuntoDiAcquisto {
    private long id;

    public Macchinetta(boolean attivo) {
        super(attivo);
    }

    public Macchinetta() {
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
        return "Macchinetta{" +
                "id=" + id +
                "} " + super.toString();
    }
}
