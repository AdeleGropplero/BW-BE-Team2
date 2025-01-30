package esami.epicode.DAO;

import esami.epicode.Entity.Abbonamento;
import esami.epicode.Entity.Biglietto;
import esami.epicode.Entity.TitoloDiViaggio;
import esami.epicode.Enum.Cadenza;
import esami.epicode.Utilities.Utilities;
import esami.epicode.entities.Veicolo;
import esami.epicode.entities.Viaggio;

import javax.persistence.EntityManager;

public class ViaggioDAO {

    private static EntityManager em;

    public ViaggioDAO(EntityManager em) {
        this.em = em;
    }

    public static VeicoloDAO veicoloDAO = new VeicoloDAO(Utilities.em);
    public static TrattaDAO trattaDAO = new TrattaDAO(Utilities.em);

    public static Viaggio t1 = new Viaggio(veicoloDAO.getByID(1), trattaDAO.getByID(1));
    public static Viaggio t2 = new Viaggio(veicoloDAO.getByID(2), trattaDAO.getByID(2));
    public static Viaggio t3 = new Viaggio(veicoloDAO.getByID(3), trattaDAO.getByID(3));
    public static Viaggio t4 = new Viaggio(veicoloDAO.getByID(4), trattaDAO.getByID(4));
    public static Viaggio t5 = new Viaggio(veicoloDAO.getByID(1), trattaDAO.getByID(1));
    public static Viaggio t6 = new Viaggio(veicoloDAO.getByID(4), trattaDAO.getByID(4));


    public void istanziaViaggio() {
        save(t1);
        save(t2);
        save(t3);
        save(t4);
        save(t5);
        save(t6);
    }

    public void save(Viaggio e) {
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }

    public Viaggio getByID(long id) {
        return em.find(Viaggio.class, id);
    }

    public void delete(Viaggio e) {
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
    }
}
