package esami.epicode.DAO;

import esami.epicode.Entity.Abbonamento;
import esami.epicode.Entity.Rivenditore_autorizzato;
import esami.epicode.Entity.TesseraUtente;
import esami.epicode.Enum.Cadenza;
import esami.epicode.Exception.tesseraNonEsistenteException;
import esami.epicode.Utilities.Utilities;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;

public class AbbonamentoDAO {
    private EntityManager em;

    public AbbonamentoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Abbonamento e) {
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }

    public Abbonamento getByID(long id) {
        return em.find(Abbonamento.class, id);
    }

    public void delete(Abbonamento e) {
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
    }

    public void verificaValiditaAbbonamento(long nTessera) {
        Query q = em.createQuery("SELECT a FROM Abbonamento a WHERE a.tessera.id = :nTessera ");
        q.setParameter("nTessera", nTessera);
        Abbonamento abbonamento = (Abbonamento) q.getSingleResult();
        if (abbonamento.getData_di_scadenza().isAfter(LocalDate.now())) {
            System.out.println("L'abbonamento è ancora VALIDO, scade il: " + abbonamento.getData_di_scadenza());

        } else {
            System.out.println("L'abbonamento è SCADUTO il: " + abbonamento.getData_di_scadenza());

            System.out.println("Recati in un rivenditore autorizzato per acquistare un nuovo abbonamento");
        }
    }

    public void acquistoAbbonamento(Rivenditore_autorizzato rivenditore) { // 🟦
        System.out.println("Acquisto abbonamento : Inserisci il tuo numero di tesseraUtente: ");
        Query q = em.createQuery("SELECT t FROM TesseraUtente t WHERE t.id = :nTessera");
        q.setParameter("nTessera", Utilities.sc.nextLong());
        Utilities.sc.nextLine();
        TesseraUtente tesseraUtente = (TesseraUtente) q.getSingleResult();
        if (tesseraUtente != null) {
            if (tesseraUtente.getScadenza().isAfter(LocalDate.now())) {
                System.out.println("Selezionare 1 per abbonamento SETTIMANALE :");
                System.out.println("Selezionare 2 per abbonamento MENSILE :");
                String tipologiaAbbonamento = Utilities.sc.nextLine();

                switch (tipologiaAbbonamento) {
                    case "1":
                        System.out.println("Hai scelto un abbonamento SETTIMANALE!");
                        Cadenza cadenzaSettimanale = Cadenza.SETTIMANALE;
                        Abbonamento a = new Abbonamento(rivenditore, cadenzaSettimanale, tesseraUtente);
                        save(a);
                        break;
                    case "2":
                        System.out.println("Hai scelto un abbonamento MENSILE!");
                        Cadenza cadenzaMensile = Cadenza.MENSILE;
                        Abbonamento b = new Abbonamento(rivenditore, cadenzaMensile, tesseraUtente);
                        save(b);
                        break;
                    default:
                        System.out.println("Per favore inserisci un valore valido");

                }
            } else {
                System.out.println("La tua tesseraUtente è scaduta, seleziona 1 per rinnovare, seleziona 2 per uscire! ");
                String sceltaUtente = Utilities.sc.nextLine();

                switch (sceltaUtente) {
                    case "1":
                        tesseraUtente.setData_attivazione(LocalDate.now());
                        System.out.println("TesseraUtente rinnovata con scadenza il giorno :" + LocalDate.now().plusDays(365));
                        break;
                    case "2":
                        System.out.println("Chiudo...");
                        Utilities.sc.close();
                        return;
                    default:
                        System.out.println("Per favore inserisci un valore valido");

                }
            }
        } else {
            throw new tesseraNonEsistenteException("Non risulta una tesseraUtente valida, per favore creane una nella sezione dedicata");

        }
    }
}
