package esami.epicode.Entity;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "punti_vendita")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Tipologia", discriminatorType = DiscriminatorType.STRING)
public abstract class PuntoVendita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String location;
    private boolean attivo;


    public PuntoVendita(String location, boolean attivo) {
        this.location = location;
        this.attivo = attivo;

    }

    public PuntoVendita() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isAttivo() {
        return attivo;
    }

    public void setAttivo(boolean attivo) {
        this.attivo = attivo;
    }


    @Override
    public String toString() {
        return "PuntoVendita{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", attivo=" + attivo +
                '}';
    }
}
