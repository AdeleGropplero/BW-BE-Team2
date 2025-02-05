package esami.epicode.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NamedQuery(name = "getPeriodoManutenzione", query = "SELECT p FROM Periodo p WHERE p.veicolo.codiceVeicolo = :codice_veicolo")
public class Periodo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "codiceVeicolo")
    private Veicolo veicolo;

    private LocalDate dataInizio;
    private LocalDate dataFine;

    public Periodo(Veicolo veicolo, LocalDate dataInizio,LocalDate dataFine) {
        this.veicolo = veicolo;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
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

    public Veicolo getVeicolo() {
        return veicolo;
    }

    public void setVeicolo(Veicolo veicolo) {
        this.veicolo = veicolo;
    }

    @Override
    public String toString() {
        return "Periodo{" +
                "id=" + id +
                ", veicolo=" + veicolo +
                ", dataInizio=" + dataInizio +
                ", dataFine=" + dataFine +
                '}';
    }
}
