package esami.epicode.DAO;

import esami.epicode.Entity.*;
import esami.epicode.Enum.Cadenza;
import esami.epicode.Exception.NessunTitoloTrovatoException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

public class PuntoVenditaDAO {
    private EntityManager em;

    public PuntoVenditaDAO(EntityManager em) {
        this.em = em;
    }

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

    public void AcquistaTitoloDiViaggio(java.util.Scanner sc) {
        AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO(em);

        System.out.println("Da quale punto vendita stai acquistando?");
        System.out.println("Seleziona -1- per le macchinette");
        System.out.println("Seleziona -2- per i rivenditori autorizzati");
        int puntoVendita = sc.nextInt();
        sc.nextLine();

        switch (puntoVendita) {
            case 1:
                //istanzia obj macchinetta
                Macchinetta macchinetta = saveMacchinetta();
                //-----------------------------------------
                System.out.println("Hai selezionato macchinette, procediamo all'acquisto del biglietto");
                //metodo acquista biglietto
                acquistaBiglietto(macchinetta);
                break;
            case 2:
                //istanzia obj rivenditore e salvati l'id
                Rivenditore_autorizzato rivenditore = saveRivenditore();
                //---------------------------------------------
                System.out.println("Hai selezionato il rivenditore autorizzato, che operazione vuoi eseguire?");
                System.out.println("Seleziona -1- per acquistare un biglietto");
                System.out.println("Seleziona -2- per acquistare un abbonamento");
                int titoloDiViaggio = sc.nextInt();
                sc.nextLine();
                switch (titoloDiViaggio) {
                    case 1:
                        //metodo acquista biglietto
                        acquistaBiglietto(rivenditore);
                        break;
                    case 2:
                        abbonamentoDAO.acquistoAbbonamento(sc, rivenditore);
                        break;
                    default:
                        System.out.println("Per favore inserisci un valore valido");

                }
                break;
            default:
                System.out.println("Per favore inserisci un valore valido");

        }
    }

    public Rivenditore_autorizzato saveRivenditore() {
        Rivenditore_autorizzato r = new Rivenditore_autorizzato(true, LocalDate.now());
        save(r);
        return r;
    }

    public Macchinetta saveMacchinetta() {
        Macchinetta m = new Macchinetta(true, LocalDate.now());
        save(m);
        return m;
    }

    public void acquistaBiglietto(PuntoVendita p) {
        Biglietto b = new Biglietto(LocalDate.now(), p);
        System.out.println("Biglietto acquistato correttamente");
    }


}
