package esami.epicode.entities;

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

    @ManyToOne
    @JoinColumn(name = "periodo_id")
    private Periodo periodo;

    private int numBigliettiVidimati;

    @ManyToOne
    @JoinColumn(name = "tratta_id")
    private Tratta tratta;

    @ManyToOne
    @JoinColumn(name = "parco_mezzi_id")
    private ParcoMezzi parcoMezzi;

    public  Veicolo(){

    }

    public Veicolo(boolean inServizio, Tratta tratta) {
        this.inServizio = inServizio;
        this.tratta = tratta;
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

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodi(Periodo periodo) {
        this.periodo = periodo;
    }

    public Tratta getTratta() {
        return tratta;
    }

    public void setTratta(Tratta tratta) {
        this.tratta = tratta;
    }

    @Override
    public String toString() {
        return "Veicolo{" +
                ", codiceVeicolo=" + codiceVeicolo +
                ", inServizio=" + inServizio +
                ", periodi=" + periodo +
                ", numBigliettiVidimati=" + numBigliettiVidimati +
                ", tratta=" + tratta;
    }

    public abstract Biglietto obliteraBiglietto(Biglietto b);

    public void setInServizio(){

        if (inServizio){
            setInServizio(false);
        }
    };


}
