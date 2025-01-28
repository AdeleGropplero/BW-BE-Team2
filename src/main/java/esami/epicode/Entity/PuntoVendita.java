package esami.epicode.Entity;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "punti_vendita")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Tipologia", discriminatorType = DiscriminatorType.STRING)
public abstract class PuntoVendita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean attivo;
    private LocalDate data_Emissione;

    public PuntoVendita(boolean attivo, LocalDate data_Emissione) {
        this.attivo = attivo;
        this.data_Emissione = data_Emissione;
    }

    public PuntoVendita() {
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

    public LocalDate getData_Emissione() {
        return data_Emissione;
    }

    public void setData_Emissione(LocalDate data_Emissione) {
        this.data_Emissione = data_Emissione;
    }

    @Override
    public String toString() {
        return "PuntoDiAcquisto{" +
                "id=" + id +
                ", attivo=" + attivo +
                ", data_Emissione=" + data_Emissione +
                '}';
    }
}
