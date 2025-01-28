package esami.epicode.DAO;

import esami.epicode.Entity.Tessera;
import esami.epicode.Entity.Utente;
import esami.epicode.Utilities.Utilities;

import javax.persistence.EntityManager;
import java.time.LocalDate;

public class TesseraDAO {


    EntityManager em;
    UtenteDAO ud = new UtenteDAO(em);

    public TesseraDAO (EntityManager em){
        this.em = em;

    }


    public void createTessera(){
        long id= Utilities.getLong("Inserisci il tuo id");
        Tessera t = new Tessera();
        Utente u = ud.findById(id);

        if(u!=null){
            t.setUtente(u);
            t.setData_attivazione(LocalDate.now());
            t.setScadenza(t.getData_attivazione().plusYears(1));
            em.persist(t);
        }else {
            System.out.println("Utente non presente sul database");
        }
    }


}
