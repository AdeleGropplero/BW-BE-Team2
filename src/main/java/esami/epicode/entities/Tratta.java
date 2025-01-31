package esami.epicode.entities;

import javax.persistence.*;
import java.time.LocalDate;
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

    @OneToMany (mappedBy = "tratta", cascade = CascadeType.ALL)
    private List<Viaggio> viaggi = new ArrayList<>();

    @OneToOne(mappedBy = "veicolo")
    private Veicolo veicolo;

    public Tratta(String partenza, String capolinea, int tempoPrevisto,Veicolo veicolo) {
        this.partenza = partenza;
        this.capolinea = capolinea;
        this.tempoPrevisto = tempoPrevisto;
        this.veicolo=veicolo;
    }



    public Tratta() {
    }

    public int calcTempoEffettivo(){
        int tempoEffettivo = (int) (tempoPrevisto + (Math.random() * 31));
        return tempoEffettivo;
    }

    public List<Viaggio> getViaggi() {
        return viaggi;
    }

    public void setViaggi(List<Viaggio> viaggi) {
        this.viaggi = viaggi;
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

    public Veicolo getVeicolo() {
        return veicolo;
    }

    public void setVeicolo(Veicolo veicolo) {
        this.veicolo = veicolo;
    }

    @Override
    public String toString() {
        return "Tratta{" +
                "id=" + id +
                ", partenza='" + partenza + '\'' +
                ", capolinea='" + capolinea + '\'' +
                ", tempoPrevisto=" + tempoPrevisto +
                ", viaggi=" + viaggi.size() +
                '}';
    }
}
