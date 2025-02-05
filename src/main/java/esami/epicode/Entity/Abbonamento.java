package esami.epicode.Entity;

import esami.epicode.Enum.Cadenza;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Abbonamento extends TitoloDiViaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private Cadenza cadenza;
    @OneToOne
    @JoinColumn(name = "tessera_id")
    private TesseraUtente tesseraUtente;
    private LocalDate data_di_scadenza;

    /*    @ManyToOne
        @JoinColumn(name = "rivenditore_autorizzato_id")
        private Rivenditore_autorizzato rivenditore_autorizzato;*/
    @ManyToOne
    @JoinColumn(name = "puntoVenditaId")
    private PuntoVendita puntoVendita;

    public Abbonamento() {
    }

    public Abbonamento(PuntoVendita puntoVendita , Cadenza cadenza, TesseraUtente tesseraUtente) {
        super(puntoVendita);
        this.cadenza = cadenza;
        this.data_di_scadenza = gestioneScadenze();
        this.tesseraUtente = tesseraUtente;
    }

    public LocalDate gestioneScadenze() {
        if (this.cadenza.equals(Cadenza.SETTIMANALE)) {
            return this.data_di_scadenza = getData_acquisto().plusDays(7);
        } else {
            return this.data_di_scadenza = getData_acquisto().plusDays(30);
        }
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public Cadenza getCadenza() {
        return cadenza;
    }

    public void setCadenza(Cadenza cadenza) {
        this.cadenza = cadenza;
    }

    public TesseraUtente getN_tessera() {
        return tesseraUtente;
    }

    public void setN_tessera_id(TesseraUtente tessera_Utente_id) {
        this.tesseraUtente = Abbonamento.this.tesseraUtente;
    }

    public LocalDate getData_di_scadenza() {
        return data_di_scadenza;
    }

    public void setData_di_scadenza(LocalDate data_di_scadenza) {
        this.data_di_scadenza = data_di_scadenza;
    }

    @Override
    public String toString() {
        return "Abbonamento{" +
                "id=" + id +
                ", cadenza=" + cadenza +
                ", tesseraUtente=" + tesseraUtente +
                ", data_di_scadenza=" + data_di_scadenza +
                "} " + super.toString();
    }
}
