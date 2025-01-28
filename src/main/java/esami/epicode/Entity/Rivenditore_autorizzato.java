package esami.epicode.Entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rivenditori_autorizzati")
public class Rivenditore_autorizzato extends PuntoVendita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "puntoVendita")
    @OrderBy("id ASC")
    private List<TitoloDiViaggio> titoliDiViaggio;


    public Rivenditore_autorizzato(boolean attivo, LocalDate data_Emissione) {
        super(attivo, data_Emissione);
    }

    public Rivenditore_autorizzato() {
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
        return "Rivenditore_autorizzato{" +
                "id=" + id +
                "} " + super.toString();
    }
}
