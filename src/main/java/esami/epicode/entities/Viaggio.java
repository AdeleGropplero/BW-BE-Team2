package esami.epicode.entities;

import javax.persistence.*;

@Entity
@Table(name = "viaggi")
public class Viaggio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn( name = "veicolo_id", nullable = false)
    private Veicolo veicolo;

    @ManyToOne
    @JoinColumn(name = "tratta_id", nullable = false)
    private Tratta tratta;

    private int tempoEffettivo;

    public Viaggio() {
    }
    //

    public Viaggio(Veicolo veicolo, Tratta tratta) {
        this.veicolo = veicolo;
        this.tratta = tratta;
        this.tempoEffettivo = tratta.calcTempoEffettivo();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Veicolo getVeicolo() {
        return veicolo;
    }

    public void setVeicolo(Veicolo veicolo) {
        this.veicolo = veicolo;
    }

    public Tratta getTratta() {
        return tratta;
    }

    public void setTratta(Tratta tratta) {
        this.tratta = tratta;
    }

    public int getTempoEffettivo() {
        return tempoEffettivo;
    }

    public void setTempoEffettivo(int tempoEffettivo) {
        this.tempoEffettivo = tempoEffettivo;
    }

    @Override
    public String toString() {
        return "Viaggio{" +
                "id=" + id +
                ", veicolo=" + veicolo.getCodiceVeicolo() +
                ", tratta=" + tratta.getPartenza() + "-"+ tratta.getCapolinea() +
                ", tempoEffettivo=" + tempoEffettivo +
                '}';
    }
}
