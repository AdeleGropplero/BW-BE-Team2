package esami.epicode.Classi;

import java.time.LocalDate;

public abstract class TitoloDiViaggio {
    private long id;
    private LocalDate dataAcquisto;
    private PuntoDiAcquisto puntoDiAcquisto;

    public TitoloDiViaggio(LocalDate dataAcquisto, PuntoDiAcquisto puntoDiAcquisto) {
        this.dataAcquisto = dataAcquisto;
        this.puntoDiAcquisto = puntoDiAcquisto;
    }

    public TitoloDiViaggio() {
    }

    public LocalDate getData_acquisto() {
        return dataAcquisto;
    }

    public void setData_acquisto(LocalDate dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PuntoDiAcquisto getPunto_di_acquisto() {
        return puntoDiAcquisto;
    }

    public void setPunto_di_acquisto(PuntoDiAcquisto puntoDiAcquisto) {
        this.puntoDiAcquisto = puntoDiAcquisto;
    }

    @Override
    public String toString() {
        return "TitoloDiViaggio{" +
                "id=" + id +
                ", dataAcquisto=" + dataAcquisto +
                ", puntoDiAcquisto=" + puntoDiAcquisto +
                '}';
    }
}
