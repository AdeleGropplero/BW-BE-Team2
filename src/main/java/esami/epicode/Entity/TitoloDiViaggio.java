package esami.epicode.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
@Entity
public abstract class TitoloDiViaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate dataAcquisto;
    private PuntoVendita puntoDiAcquisto;

    public TitoloDiViaggio(LocalDate dataAcquisto, PuntoVendita puntoDiAcquisto) {
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

    public PuntoVendita getPunto_di_acquisto() {
        return puntoDiAcquisto;
    }

    public void setPunto_di_acquisto(PuntoVendita puntoDiAcquisto) {
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
