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

    public Macchinetta(String location, boolean attivo) {
        super(location, attivo);
       // this.biglietti = biglietti;
    }

    public Macchinetta() {
    }

    public List<Biglietto> getBiglietti() {
        return biglietti;
    }

    public void setBiglietti(List<Biglietto> biglietti) {
        this.biglietti = biglietti;
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
