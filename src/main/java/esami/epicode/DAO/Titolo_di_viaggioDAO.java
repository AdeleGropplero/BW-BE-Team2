package esami.epicode.DAO;

import esami.epicode.Entity.*;
import esami.epicode.Enum.Cadenza;
import esami.epicode.Utilities.Utilities;
import esami.epicode.entities.Veicolo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Titolo_di_viaggioDAO {

    private EntityManager em;

    public Titolo_di_viaggioDAO(EntityManager em) {
        this.em = em;
    }

    public static PuntoVenditaDAO puntoVenditaDAO = new PuntoVenditaDAO(Utilities.em);
    public static TesseraDAO tesseraDAO = new TesseraDAO(Utilities.em);

    public static TitoloDiViaggio t1 = new Biglietto(puntoVenditaDAO.getByID(1));
    public static TitoloDiViaggio t2 = new Biglietto(puntoVenditaDAO.getByID(1));
    public static TitoloDiViaggio t3 = new Biglietto(puntoVenditaDAO.getByID(5));
    public static TitoloDiViaggio t4 = new Abbonamento(puntoVenditaDAO.getByID(5), Cadenza.MENSILE, tesseraDAO.getByID(1));
    public static TitoloDiViaggio t5 = new Abbonamento(puntoVenditaDAO.getByID(6), Cadenza.SETTIMANALE, tesseraDAO.getByID(3));
    public static TitoloDiViaggio t6 = new Abbonamento(puntoVenditaDAO.getByID(8), Cadenza.MENSILE, tesseraDAO.getByID(5));

    public void istanziaTitoloDiViaggio() {
        save(t1);
        save(t2);
        save(t3);
        save(t4);
        save(t5);
        save(t6);
    }

    public void save(TitoloDiViaggio e) {
        Utilities.em.getTransaction().begin();
        Utilities.em.persist(e);
        Utilities.em.getTransaction().commit();
    }

    public TitoloDiViaggio getByID(long id) {
        return Utilities.em.find(TitoloDiViaggio.class, id);
    }

    public void delete(TitoloDiViaggio e) {
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
    }

    public void acquistaBiglietto(PuntoVendita p) {
        TitoloDiViaggio t = new Biglietto(p);
        save(t);
        System.out.println("Biglietto acquistato correttamente");
    }
}


