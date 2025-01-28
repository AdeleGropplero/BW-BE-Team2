package esami.epicode.DAO;

import esami.epicode.Entity.Abbonamento;
import esami.epicode.Entity.PuntoVendita;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;

public class AbbonamentoDAO {
    private EntityManager em;

    public AbbonamentoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(PuntoVendita e) {
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }

    public PuntoVendita getByID(long id) {
        return em.find(PuntoVendita.class, id);
    }

    public void delete(PuntoVendita e) {
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
    }

    public void verificaValiditaAbbonamento(long nTessera) {
        Query q = em.createQuery("SELECT a FROM Abbonamento a WHERE a.tessera.id = :nTessera ");
        q.setParameter("nTessera", nTessera);
        Abbonamento abbonamento = (Abbonamento) q.getSingleResult();
        if (abbonamento.getData_di_scadenza().isAfter(LocalDate.now())) {
            System.out.println("L'abbonamento è ancora VALIDO, scade il: " + abbonamento.getData_di_scadenza());

        }else {
            System.out.println("L'abbonamento è SCADUTO il: " + abbonamento.getData_di_scadenza());
            //chiama metodo rinnovo 🟥
        }
    }
}
