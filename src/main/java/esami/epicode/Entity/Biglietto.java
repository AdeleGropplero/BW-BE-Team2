package esami.epicode.Entity;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table (name = "biglietti")
public class Biglietto extends TitoloDiViaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate utilizzabile;

    @ManyToOne
    @JoinColumn(name = "rivenditore_autorizzato_id")
    private Rivenditore_autorizzato rivenditore_autorizzato;

    @ManyToOne
    @JoinColumn(name = "macchinetta_id")
    private Macchinetta macchinetta;

    public Biglietto(LocalDate data_acquisto, PuntoVendita punto_di_acquisto) {
        super(data_acquisto, punto_di_acquisto);
        this.utilizzabile = null;
    }

    public Biglietto() {
    }

    public LocalDate isUtilizzabile() {
        return utilizzabile;
    }

    public void setUtilizzabile(LocalDate utilizzabile) {
        this.utilizzabile = utilizzabile;
    }

    public LocalDate getUtilizzabile() {
        return utilizzabile;
    }

    public Rivenditore_autorizzato getRivenditore_autorizzato() {
        return rivenditore_autorizzato;
    }

    public Macchinetta getMacchinetta() {
        return macchinetta;
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
