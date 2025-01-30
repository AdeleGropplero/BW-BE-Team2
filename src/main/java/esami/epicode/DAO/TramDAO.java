package esami.epicode.DAO;

import esami.epicode.Entity.Biglietto;
import esami.epicode.Entity.Macchinetta;
import esami.epicode.entities.Periodo;
import esami.epicode.entities.Tram;
import esami.epicode.entities.Veicolo;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class TramDAO {

    EntityManager em ;

    public TramDAO (EntityManager em){
        this.em = em;
    }

    public Tram findById(long id){
        return em.find(Tram.class, id);
    }

    public void save(Tram e) {
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }


    public void delete(Tram e) {
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
    }

/*
    public static Tram t1 = new Tram(true);
    public static Tram t2 = new Tram(true);
    public static Tram t3 = new Tram(false);

    public void istanziaTram() {
        save(t1);
        save(t2);
        save(t3);

    }*/

}
