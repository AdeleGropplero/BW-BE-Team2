package esami.epicode.Entity;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "titoli_di_viaggio")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Tipologia", discriminatorType = DiscriminatorType.STRING)
public abstract class TitoloDiViaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate dataAcquisto;
    @ManyToOne
    @JoinColumn(name = "puntoVendita_id")
    private PuntoVendita puntoVendita;



    public TitoloDiViaggio( PuntoVendita puntoVendita) {
        this.dataAcquisto = LocalDate.now();
        this.puntoVendita = puntoVendita;;

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
        return puntoVendita;
    }

    public void setPunto_di_acquisto(PuntoVendita puntoVendita) {
        this.puntoVendita = puntoVendita;
    }

    @Override
    public String toString() {
        return "TitoloDiViaggio{" +
                "id=" + id +
                ", dataAcquisto=" + dataAcquisto +
                ", puntoVendita=" + puntoVendita +
                '}';
    }
}
