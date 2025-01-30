package esami.epicode.Entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Rivenditore_autorizzato extends PuntoVendita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

/*    @OneToMany(mappedBy = "puntoVendita")
    @OrderBy("id ASC")
    private List<TitoloDiViaggio> titoliDiViaggio;*/


    public Rivenditore_autorizzato(String location, boolean attivo) {
        super(location, attivo);
       // this.titoliDiViaggio = titoliDiViaggio;
    }

    public Rivenditore_autorizzato() {
    }

    public List<TitoloDiViaggio> getTitoliDiViaggio() {
        return titoliDiViaggio;
    }

    public void setTitoliDiViaggio(List<TitoloDiViaggio> titoliDiViaggio) {
        this.titoliDiViaggio = titoliDiViaggio;
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
