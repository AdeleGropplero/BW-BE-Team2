package esami.epicode.DAO;

import esami.epicode.Entity.PuntoVendita;
import esami.epicode.entities.Periodo;

import javax.persistence.EntityManager;

public class PeriodoDAO {
    private EntityManager em;

    public PeriodoDAO(EntityManager em) {
        this.em = em;
    }


    public void save(Periodo p) {
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }
}
