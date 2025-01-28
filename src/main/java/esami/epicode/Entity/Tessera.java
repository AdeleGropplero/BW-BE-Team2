package esami.epicode.Entity;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "tessere")
public class Tessera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate data_attivazione;
    private LocalDate scadenza;

    @OneToOne(mappedBy = "tessera")
    private Abbonamento abbonamento;

    @OneToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    public Tessera() {
    }

    public Tessera(LocalDate data_attivazione) {
        this.data_attivazione = data_attivazione;
        this.scadenza = getData_attivazione().plusDays(365);

        //this.id_utente = id_utente;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getData_attivazione() {
        return data_attivazione;
    }

    public void setData_attivazione(LocalDate data_attivazione) {
        this.data_attivazione = data_attivazione;
    }

    public LocalDate getScadenza() {
        return scadenza;
    }

    public void setScadenza(LocalDate scadenza) {
        this.scadenza = scadenza;
    }

    public Abbonamento getAbbonamento() {
        return abbonamento;
    }

    public void setAbbonamento(Abbonamento abbonamento) {
        this.abbonamento = abbonamento;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    @Override
    public String toString() {
        return "Tessera{" +
                "id=" + id +
                ", data_attivazione=" + data_attivazione +
                ", scadenza=" + scadenza +
                ", abbonamento=" + abbonamento +
                ", utente=" + utente +
                '}';
    }
}
