package esami.epicode.Entity;

import esami.epicode.Enum.Cadenza;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Abbonamento extends TitoloDiViaggio{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private Cadenza cadenza;
    @OneToOne
    @JoinColumn(name = "tessera_id")
    private long n_tessera;
    private LocalDate data_di_scadenza;

    @ManyToOne
    @JoinColumn(name = "rivenditore_autorizzato_id")
    private Rivenditore_autorizzato rivenditore_autorizzato;

    public Abbonamento() {
    }

    public Abbonamento(LocalDate dataAcquisto, PuntoVendita puntoDiAcquisto, Cadenza cadenza, long n_tessera) {
        super(dataAcquisto, puntoDiAcquisto);
        this.cadenza = cadenza;
        this.n_tessera = n_tessera;
        this.data_di_scadenza = gestioneScadenze();
    }

    public LocalDate gestioneScadenze(){
        if (this.cadenza.equals(Cadenza.SETTIMANALE)){
           return this.data_di_scadenza = getData_acquisto().plusDays(7);
        }else {
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

    public long getN_tessera() {
        return n_tessera;
    }

    public void setN_tessera(long n_tessera) {
        this.n_tessera = n_tessera;
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
                ", n_tessera=" + n_tessera +
                ", data_di_scadenza=" + data_di_scadenza +
                "} " + super.toString();
    }
}
