package esami.epicode.DAO;

import esami.epicode.Entity.Macchinetta;
import esami.epicode.Entity.Rivenditore_autorizzato;
import esami.epicode.Exception.NessunTitoloTrovatoException;
import esami.epicode.Utilities.Utilities;

import javax.persistence.EntityManager;

public class Rivenditore_autorizzatoDAO {
    private EntityManager em;

    public Rivenditore_autorizzatoDAO(EntityManager em) {
        this.em = em;
    }

    public static Rivenditore_autorizzato r1 = new Rivenditore_autorizzato("Piazza Mancini", true);
    public static Rivenditore_autorizzato r2 = new Rivenditore_autorizzato("Piazza della Repubblica", true);
    public static Rivenditore_autorizzato r3 = new Rivenditore_autorizzato("Piazza Fiume", false);
    public static Rivenditore_autorizzato r4 = new Rivenditore_autorizzato("Piazza Navona", true);


    public void save(Rivenditore_autorizzato e) {
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }

    public Rivenditore_autorizzato getByID(long id) {
        return em.find(Rivenditore_autorizzato.class, id);
    }

    public void delete(Rivenditore_autorizzato e) {
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
    }

    public void istanziaRivenditore() {
        save(r1);
        save(r2);
        save(r3);
        save(r4);
    }

    public Rivenditore_autorizzato sceltaRivenditore_autorizzato() {
        System.out.println("In quale rivenditore ti trovi?");
        System.out.println("-1- Piazza Mancini");
        System.out.println("-2- Piazza della Repubblica");
        System.out.println("-3- Piazza Fiume");
        System.out.println("-4- Piazza Navona");

        String scelta = Utilities.sc.nextLine();

        if (scelta.equals("1")) {
            System.out.println("Hai selezionato il rivenditore di Piazza Mancini, che operazione vuoi eseguire?");
            return r1;
        } else if (scelta.equals("2")) {
            System.out.println("Hai selezionato il rivenditore di Piazza della Repubblica, che operazione vuoi eseguire?");
            return r2;
        } else if (scelta.equals("3")) {
            System.out.println("Hai selezionato il rivenditore di Piazza Fiume, che operazione vuoi eseguire?");
            return r3;
        } else if (scelta.equals("4")) {
            System.out.println("Hai selezionato il rivenditore di Piazza Navona, che operazione vuoi eseguire?");
            return r4;
        } else {
            System.out.println("Per favore seleziona un valore valido");
            throw new NessunTitoloTrovatoException("Non è stato possibile continuare, riprova.");
        }
    }


}
