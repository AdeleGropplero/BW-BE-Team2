package esami.epicode.DAO;

import esami.epicode.Entity.Biglietto;
import esami.epicode.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class VeicoloDAO {



    private EntityManager em;

    Veicolo v1 = new Autobus(true ); // capire meglio domani // 🟥 da sistemare
    Veicolo v2 = new Autobus(true);
    Veicolo v5 = new Autobus(false);
    Veicolo v3 = new Tram(true);
    Veicolo v4 = new Tram(true);
    Veicolo v6 = new Tram(false);

    public VeicoloDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Veicolo e) {
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }

    public Veicolo getByID(long id) {
        return em.find(Veicolo.class, id);
    }

    public void delete(Veicolo e) {
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
    }

    public void istanziaVeicoli() {
        save(v1);
        save(v2);
        save(v3);
        save(v4);
        save(v5);
        save(v6);
    }

    public void increment(Veicolo t) {
        String sql = "UPDATE Tram b SET b.numBigliettiVidimati + 1 WHERE b.id = :id";

        em.getTransaction().begin();

        TypedQuery<Periodo> query = em.createQuery(sql, Periodo.class);

        query.setParameter("id", t.getCodiceVeicolo());

        em.getTransaction().commit();
    }

    public void vidimaBiglietto(Biglietto b, Veicolo t) {
        if (b.getUtilizzabile() == null) {
            String sql = "UPDATE Biglietto b SET b.utilizzabile = CURRENT_DATE WHERE b.id = :id";

            em.getTransaction().begin();

            TypedQuery<Periodo> query = em.createQuery(sql, Periodo.class);

            query.setParameter("id", b.getId());

            increment(t);

            em.getTransaction().commit();
        }

    }



}
