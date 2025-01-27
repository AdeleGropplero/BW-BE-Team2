package esami.epicode.Entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "macchinette")
public class Macchinetta extends PuntoVendita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "macchinetta")
    List<Biglietto> biglietti = new ArrayList<>();

    public Macchinetta(boolean attivo, LocalDate data_Emissione) {
        super(attivo, data_Emissione);
    }

    public Macchinetta() {
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Macchinetta{" +
                "id=" + id +
                "} " + super.toString();
    }
}
