package esami.epicode.DAO;

import esami.epicode.Entity.Macchinetta;
import esami.epicode.Entity.PuntoVendita;
import esami.epicode.Exception.NessunTitoloTrovatoException;
import esami.epicode.Utilities.Utilities;

import javax.persistence.EntityManager;
import java.time.LocalDate;

public class MacchinettaDAO {
    private EntityManager em;

    public MacchinettaDAO(EntityManager em) {
        this.em = em;
    }

    public static Macchinetta m1 = new Macchinetta("Piazza di Spagna", true);
    public static Macchinetta m2 = new Macchinetta("Piazza dei Mirti", true);
    public static Macchinetta m3 = new Macchinetta("Piazza Venezia", false);
    public static Macchinetta m4 = new Macchinetta("Trastevere", true);


    public void save(Macchinetta e) {
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }

    public Macchinetta getByID(long id) {
        return em.find(Macchinetta.class, id);
    }

    public void delete(Macchinetta e) {
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
    }

    public void istanziaMacchinette() {
        save(m1);
        save(m2);
        save(m3);
        save(m4);
    }

    public Macchinetta sceltaMacchinetta() {
        System.out.println("In quale macchinetta ti trovi?");
        System.out.println("-1- Piazza di Spagna");
        System.out.println("-2- Piazza dei Mirti");
        System.out.println("-3- Piazza Venezia");
        System.out.println("-4- Trastevere");

        String scelta = Utilities.sc.nextLine();

        if (scelta.equals("1")) {
            System.out.println("Hai selezionato macchinetta di Piazza di Spagna, procediamo all'acquisto del biglietto");
            return m1;
        } else if (scelta.equals("2")) {
            System.out.println("Hai selezionato macchinetta di Piazza dei Mirti, procediamo all'acquisto del biglietto");
            return m2;
        } else if (scelta.equals("3")) {
            System.out.println("Hai selezionato macchinetta di Piazza Venezia, procediamo all'acquisto del biglietto");
            return m3;
        } else if (scelta.equals("4")) {
            System.out.println("Hai selezionato macchinetta di Trastevere, procediamo all'acquisto del biglietto");
            return m4;
        } else {
            System.out.println("Per favore seleziona un valore valido");
            throw new NessunTitoloTrovatoException("Non è stato possibile continuare, riprova.");
        }
    }


}
