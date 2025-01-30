package esami.epicode.DAO;

import esami.epicode.entities.Autobus;
import esami.epicode.entities.Tram;

import javax.persistence.EntityManager;

public class AutobusDAO {
    EntityManager em ;

    public AutobusDAO (EntityManager em){
        this.em = em;
    }

    public Autobus findById(long id){
        return em.find(Autobus.class, id);
    }

    public void save(Autobus e) {
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }


    public void delete(Autobus e) {
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
    }

/*    public static Autobus a1 = new Autobus(true);
    public static Autobus a2 = new Autobus(true);
    public static Autobus a3 = new Autobus(false);

    public void istanziaAutobus() {
        save(a1);
        save(a2);
        save(a3);

    }*/
}
