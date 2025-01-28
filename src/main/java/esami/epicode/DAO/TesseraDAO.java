package esami.epicode.DAO;


import esami.epicode.Entity.Tessera;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Scanner;

public class TesseraDAO {
    private EntityManager em;
    public TesseraDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Tessera e) {
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }

    public Tessera getByID(long id) {
        return em.find(Tessera.class, id);
    }

    public void delete(Tessera e) {
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
    }

}
