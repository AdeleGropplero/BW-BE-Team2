package esami.epicode.DAO;

import esami.epicode.Entity.Utente;
import esami.epicode.Utilities.Utilities;

import javax.persistence.EntityManager;

public class UtenteDAO {

    EntityManager em ;

    public UtenteDAO (EntityManager em){
        this.em = em;
    }

    public Utente createUtente(){
        Utente u = new Utente();
        u.setNome(Utilities.getString("Inserisci il tuo nome"));
        u.setCognome(Utilities.getString("Inserisci il tuo cognome"));
        u.setData_di_nascita(Utilities.getDate("Inserisci la tua data di nascita (Formato 'dd/MM/yyyy') "));

        em.persist(u);

        System.out.println("Utente creato con successo");
        return u;
    }

    public Utente findById(long id){
        return em.find(Utente.class, id);

    }
}
