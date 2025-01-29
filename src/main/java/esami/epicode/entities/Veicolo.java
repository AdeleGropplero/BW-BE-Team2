package esami.epicode.entities;

import esami.epicode.Entity.Biglietto;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipologia", discriminatorType = DiscriminatorType.STRING)
public abstract class Veicolo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codiceVeicolo;


    private boolean inServizio;

    @OneToMany(mappedBy = "veicolo", cascade = CascadeType.ALL)
    private List<Periodo> periodi;

    private int numBigliettiVidimati;

    @ManyToMany
    @JoinTable(name = "veicolo_tratta",
            joinColumns = @JoinColumn(name = "veicolo_id"),
            inverseJoinColumns = @JoinColumn(name = "tratta_id"))
    private List<Tratta> tratte;

    @ManyToOne
    @JoinColumn(name = "parco_mezzi_id")
    private ParcoMezzi parcoMezzi;

    public Veicolo() {

    }

    public Veicolo(boolean inServizio) {
        this.inServizio = inServizio;
        this.tratte = new ArrayList<>();
        this.numBigliettiVidimati = 0;

    }


    public long getCodiceVeicolo() {
        return codiceVeicolo;
    }

    public void setCodiceVeicolo(long codiceVeicolo) {
        this.codiceVeicolo = codiceVeicolo;
    }

    public boolean isInServizio() {
        return inServizio;
    }

    public void setInServizio(boolean inServizio) {
        this.inServizio = inServizio;
    }

    public int getNumBigliettiVidimati() {
        return numBigliettiVidimati;
    }

    public void setNumBigliettiVidimati(int numBigliettiVidimati) {
        this.numBigliettiVidimati = numBigliettiVidimati;
    }

    public List<Periodo> getPeriodi() {
        return periodi;
    }

    public void setPeriodi(List<Periodo> periodi) {
        this.periodi = periodi;
    }

    public List<Tratta> getTratta() {
        return this.tratte;
    }

    public void setTratta(List<Tratta> tratte) {
        this.tratte = tratte;
    }


    @Override
    public String toString() {
        return "Veicolo{" +
                ", codiceVeicolo=" + codiceVeicolo +
                ", inServizio=" + inServizio +
                ", periodi=" + periodi +
                ", numBigliettiVidimati=" + numBigliettiVidimati +
                ", tratte=" + tratte;
    }

    public abstract Biglietto obliteraBiglietto(Biglietto b);

    public void setInServizio() {

        if (inServizio) {
            setInServizio(false);
        }
    }

    ;


}
