package esami.epicode.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Periodo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "veicolo_codVeicolo")
    private Veicolo veicolo;
    private LocalDate dataInizio;
    private LocalDate dataFine;

    public Periodo(LocalDate dataFine, LocalDate dataInizio) {
        this.dataFine = dataFine;
        this.dataInizio = dataInizio;
    }



    public Periodo() {
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }


    @Override
    public String toString() {
        return "Periodo{" +
                "dataFine=" + dataFine +
                ", dataInizio=" + dataInizio +
                '}';
    }


}
