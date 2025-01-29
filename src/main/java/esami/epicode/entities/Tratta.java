package esami.epicode.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tratta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String partenza;
    private String capolinea;
    private int tempoPrevisto;
    private int tempoPrevistoAutobus;
    private int tempoPrevistoTram;


    @OneToMany(mappedBy = "tratta", cascade = CascadeType.ALL)
    private List<Veicolo> veicoli = new ArrayList<>();

    public Tratta(String partenza, String capolinea, int tempoPrevisto) {
        this.partenza = partenza;
        this.capolinea = capolinea;
        this.tempoPrevisto = tempoPrevisto;
    }

    public Tratta() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPartenza() {
        return partenza;
    }

    public void setPartenza(String partenza) {
        this.partenza = partenza;
    }

    public String getCapolinea() {
        return capolinea;
    }

    public void setCapolinea(String capolinea) {
        this.capolinea = capolinea;
    }

    public int getTempoPrevisto() {
        return tempoPrevisto;
    }

    public void setTempoPrevisto(int tempoPrevisto) {
        this.tempoPrevisto = tempoPrevisto;
    }

    public int getTempoPrevistoAutobus() {
        return tempoPrevistoAutobus;
    }

    public void setTempoPrevistoAutobus(int tempoPrevistoAutobus) {
        this.tempoPrevistoAutobus = tempoPrevistoAutobus;
    }

    public int getTempoPrevistoTram() {
        return tempoPrevistoTram;
    }

    public void setTempoPrevistoTram(int tempoPrevistoTram) {
        this.tempoPrevistoTram = tempoPrevistoTram;
    }

    @Override
    public String toString() {
        return "Tratta{" +
                "id=" + id +
                ", partenza='" + partenza + '\'' +
                ", capolinea='" + capolinea + '\'' +
                ", tempoPrevisto=" + tempoPrevisto +
                '}';
    }
}
