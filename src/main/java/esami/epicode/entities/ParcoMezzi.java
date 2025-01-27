package esami.epicode.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ParcoMezzi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "parcoMezzi")
    private List<Veicolo> veicoli = new ArrayList<>();

    @OneToMany(mappedBy = "parcoMezzi")
    private List<Veicolo> periodiVeicoli;

    public ParcoMezzi() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Veicolo> getPeriodiVeicoli() {
        return periodiVeicoli;
    }

    public void setPeriodiVeicoli(List<Veicolo> periodiVeicoli) {
        this.periodiVeicoli = periodiVeicoli;
    }

    public List<Veicolo> getVeicoli() {
        return veicoli;
    }

    public void setVeicoli(List<Veicolo> veicoli) {
        this.veicoli = veicoli;
    }

    public void putManutenzione(Veicolo a){
        a.getPeriodo().setDataInizio(LocalDate.now());
        periodiVeicoli.add(a);
    }

    public void leftManutenzione(long codiceVeicolo, LocalDate data){
        for(Veicolo veicolo : periodiVeicoli){
            if(veicolo.getCodiceVeicolo()==codiceVeicolo){
                veicolo.getPeriodo().setDataFine(data);
            }
        }
    }

    public void printPeriodi(){
        for(Veicolo veicolo : periodiVeicoli){
            System.out.println("Veicolo: " + veicolo.getCodiceVeicolo() + " inizio manutenzione: " +veicolo.getPeriodo().getDataInizio());
            System.out.println("fine manutenzione: " +veicolo.getPeriodo().getDataFine());
        }
    }

    public List<Periodo> getPeriodiByCodiceVeicolo (long codiceVeicolo){
        List<Periodo> periodi = new ArrayList<>();
        for(Veicolo veicolo : periodiVeicoli){
            if(veicolo.getCodiceVeicolo()== codiceVeicolo){
                periodi.add(veicolo.getPeriodo());
            }
        }
        return periodi;
    }
}
