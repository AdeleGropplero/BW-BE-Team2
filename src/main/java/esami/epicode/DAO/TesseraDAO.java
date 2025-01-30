package esami.epicode.DAO;

import esami.epicode.Entity.Macchinetta;
import esami.epicode.Entity.TesseraUtente;
import esami.epicode.Utilities.Utilities;

import javax.persistence.EntityManager;
import java.time.LocalDate;

public class TesseraDAO {

    EntityManager em;

    public static TesseraUtente t1 = new TesseraUtente("Martina", "Fantuzzi", LocalDate.of(1991, 12, 9));
    public static TesseraUtente t2 = new TesseraUtente("Cristian", "Petta", LocalDate.of(1991, 3, 14));
    public static TesseraUtente t3 = new TesseraUtente("Adele", "Gropplero di Troppenburg", LocalDate.of(1998, 2, 19));
    public static TesseraUtente t4 = new TesseraUtente("Federico", "Franzi", LocalDate.of(1994, 5,31 ));
    public static TesseraUtente t5 = new TesseraUtente("Andrea", "Saccomanni", LocalDate.of(1997, 7,19 ));
    public static TesseraUtente t6 = new TesseraUtente("Gioele", "Piazza", LocalDate.of(2000, 1,1 ));

    public void istanziaTessereUtente() {
        save(t1);
        save(t2);
        save(t3);
        save(t4);
        save(t5);
        save(t6);
    }

    public TesseraDAO (EntityManager em){
        this.em = em;

    }

    public void save(TesseraUtente e) {
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }

    public TesseraUtente getByID(long id) {
        return em.find(TesseraUtente.class, id);
    }

    public void delete(TesseraUtente e) {
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
    }

    public void createTessera(){
      //  long id= Utilities.getLong("Inserisci il tuo id");
        TesseraUtente t = new TesseraUtente();
        t.setNome(Utilities.getString("Inserisci il tuo nome"));
        t.setCognome(Utilities.getString("Inserisci il tuo cognome"));
        t.setData_di_nascita(Utilities.getDate("Inserisci la tua data di nascita (Formato 'dd/MM/yyyy') "));

        em.persist(t);

        System.out.println("TesseraUtente utente generata con successo! Benvenuto in EpiTrans (C.A.G.A.M.F.");
    }


}
