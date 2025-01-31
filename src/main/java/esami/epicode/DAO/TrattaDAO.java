package esami.epicode.DAO;

import esami.epicode.Entity.PuntoVendita;
import esami.epicode.entities.Tratta;

import javax.persistence.EntityManager;

public class TrattaDAO {
    private final EntityManager em;

    Tratta t1 = new Tratta("Colosseo", "Piazza Cavour", 15);
    Tratta t2 = new Tratta("Piazza Mirti", "Piramide", 35);
    Tratta t3 = new Tratta("Vaticano", "Ponte Mammolo", 25);
    Tratta t4 = new Tratta("Prati", "Parioli", 20);

    public TrattaDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Tratta e) {
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }

    public Tratta getByID(long id) {
        return em.find(Tratta.class, id);
    }

    public void delete(Tratta e) {
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
    }

    public void istanziaTratta() {
        save(t1);
        save(t2);
        save(t3);
        save(t4);
    }


}
