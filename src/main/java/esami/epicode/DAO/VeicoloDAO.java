package esami.epicode.DAO;

import esami.epicode.Entity.Biglietto;
import esami.epicode.entities.Periodo;
import esami.epicode.entities.Veicolo;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class VeicoloDAO {

    EntityManager em ;

    public VeicoloDAO (EntityManager em ){
        this.em = em;
    }

    public void increment(Veicolo t){
        String sql = "UPDATE Tram b SET b.numBigliettiVidimati + 1 WHERE b.id = :id";

        em.getTransaction().begin();

        TypedQuery<Periodo> query = em.createQuery(sql, Periodo.class);

        query.setParameter("id", t.getCodiceVeicolo());

        em.getTransaction().commit();
    }

    public void vidimaBiglietto(Biglietto b, Veicolo t) {
        if (b.isUtilizzabile()) {
            String sql = "UPDATE Biglietto b SET b.utilizzabile = false WHERE b.id = :id";

            em.getTransaction().begin();

            TypedQuery<Periodo> query = em.createQuery(sql, Periodo.class);

            query.setParameter("id", b.getId());

            increment(t);

            em.getTransaction().commit();
        }

    }


}
