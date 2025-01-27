package esami.epicode.entities;

import java.time.LocalDate;

public class Periodo {
    private LocalDate dataInizio;
    private LocalDate dataFine;

    public Periodo(LocalDate dataFine, LocalDate dataInizio) {
        this.dataFine = dataFine;
        this.dataInizio = dataInizio;
    }



    public Periodo() {
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
