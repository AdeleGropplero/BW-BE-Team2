package esami.epicode.DAO;

import esami.epicode.Entity.*;
import esami.epicode.Enum.Cadenza;
import esami.epicode.Exception.NessunTitoloTrovatoException;
import esami.epicode.Utilities.Utilities;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

public class PuntoVenditaDAO {
    private EntityManager em;

    public PuntoVenditaDAO(EntityManager em) {
        this.em = em;
    }

    MacchinettaDAO macchinettaDAO = new MacchinettaDAO(em);
    Rivenditore_autorizzatoDAO rivenditoreDAO = new Rivenditore_autorizzatoDAO(em);
    UtenteDAO utenteDAO = new UtenteDAO(em);
    TesseraDAO tesseraDAO = new TesseraDAO(em);

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

    public List<TitoloDiViaggio> findTitoloEmessiPerSpotEPeriodo(LocalDate dataIniziale, LocalDate dataFinale, long id) {
        Query q = em.createQuery("SELECT t FROM TitoloDiViaggio t WHERE t.dataAcquisto BETWEEN :dataIniziale AND : dataFinale AND t.puntoVendita.id = :id ");
        q.setParameter("dataIniziale", dataIniziale);
        q.setParameter("dataFinale", dataFinale);
        q.setParameter("id", id);
        List<TitoloDiViaggio> risultati = q.getResultList();
        if (risultati.isEmpty()) {
            throw new NessunTitoloTrovatoException(
                    "Non sono stati trovati titoli per il periodo selezionato e/o nel punto vendita cercato"
            );
        }
        return risultati;
    }

    public void operazioniUtente() {
        AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO(em);

        System.out.println("Da quale punto vendita stai acquistando?");
        System.out.println("Seleziona -1- per le macchinette");
        System.out.println("Seleziona -2- per i rivenditori autorizzati");
        int puntoVendita = Utilities.sc.nextInt();
        Utilities.sc.nextLine();

        switch (puntoVendita) {
            case 1:
                //All'utente verrà chiesto di selezionare la location della macchinetta (vedi MacchinettaDAO)
                Macchinetta macchinetta = macchinettaDAO.sceltaMacchinetta();
                //Verrà ora istanziato e salvato un oggetto biglietto, con location specifica.
                acquistaBiglietto(macchinetta);
                break;
            case 2:

                Rivenditore_autorizzato rivenditore = rivenditoreDAO.sceltaRivenditore_autorizzato();

                System.out.println("Seleziona -1- per acquistare un biglietto");
                System.out.println("Seleziona -2- per acquistare un abbonamento");
                System.out.println("Seleziona -3- per creare una nuova tessera");

                String titoloDiViaggio = Utilities.sc.nextLine();

                switch (titoloDiViaggio) {
                    case "1":
                        //metodo acquista biglietto 🟩
                        acquistaBiglietto(rivenditore);
                        break;
                    case "2":
                        //metodo acquisto abbonamento 🟩
                        abbonamentoDAO.acquistoAbbonamento(rivenditore);
                        break;
                    case  "3":
                        //metodo registrazione utente 🟩
                        Utente utente = utenteDAO.createUtente();
                        tesseraDAO.createTessera(utente.getId());
                        break;
                    default:
                        System.out.println("Per favore inserisci un valore valido");

                }
                break;
            default:
                System.out.println("Per favore inserisci un valore valido");

        }
    }

/*    public Rivenditore_autorizzato saveRivenditore() {
        Rivenditore_autorizzato r = new Rivenditore_autorizzato(true, LocalDate.now());
        save(r);
        return r;
    }

    public Macchinetta saveMacchinetta() {
        Macchinetta m = new Macchinetta(true, LocalDate.now());
        save(m);
        return m;
    }*/

    public void acquistaBiglietto(PuntoVendita p) {
        Biglietto b = new Biglietto(LocalDate.now(), p);
        System.out.println("Biglietto acquistato correttamente");
    }


}
