package esami.epicode.DAO;

import esami.epicode.Entity.PuntoVendita;
import esami.epicode.Entity.TitoloDiViaggio;
import esami.epicode.Exception.NessunTitoloTrovatoException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

public class PuntoVenditaDAO {
    private EntityManager em;
    public PuntoVenditaDAO(EntityManager em) {
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

    public List<TitoloDiViaggio> findTitoloEmessiPerSpotEPeriodo(LocalDate dataIniziale, LocalDate dataFinale, long id){
        Query q = em.createQuery("SELECT t FROM TitoloDiViaggio t WHERE t.dataAcquisto BETWEEN :dataIniziale AND : dataFinale AND t.puntoVendita.id = :id ");
        q.setParameter("dataIniziale", dataIniziale);
        q.setParameter("dataFinale", dataFinale);
        q.setParameter("id", id);
        List<TitoloDiViaggio> risultati = q.getResultList();
        if (risultati.isEmpty()){
            throw new NessunTitoloTrovatoException(
                    "Non sono stati trovati titoli per il periodo selezionato e/o nel punto vendita cercato"
            );
        }
        return risultati;
    }

}
