package esami.epicode.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ParcoMezzi {

    private List<Veicolo> veicoli = new ArrayList<>();
    private List<Veicolo> periodiVeicoli;


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
