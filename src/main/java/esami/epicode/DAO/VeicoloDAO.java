package esami.epicode.DAO;

import esami.epicode.Entity.Biglietto;
import esami.epicode.Utilities.Utilities;
import esami.epicode.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class VeicoloDAO {

    private EntityManager em;

    Titolo_di_viaggioDAO biglietto = new Titolo_di_viaggioDAO(em);



    Veicolo v1 = new Autobus(true); // capire meglio domani // 🟥 da sistemare
    Veicolo v2 = new Autobus(true);
    Veicolo v5 = new Autobus(false);
    Veicolo v3 = new Tram(true);
    Veicolo v4 = new Tram(true);
    Veicolo v6 = new Tram(false);

    public VeicoloDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Veicolo e) {
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }

    public Veicolo getByID(long id) {
        return em.find(Veicolo.class, id);
    }

    public void delete(Veicolo e) {
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
    }

    public void istanziaVeicoli() {
        save(v1);
        save(v2);
        save(v3);
        save(v4);
        save(v5);
        save(v6);
    }

    public void increment(Veicolo t) {
        // La query HQL corretta per incrementare numBigliettiVidimati
        String sql = "UPDATE Tram b SET b.numBigliettiVidimati = b.numBigliettiVidimati + 1 WHERE b.id = :id";

        Query query = em.createQuery(sql);
        query.setParameter("id", t.getCodiceVeicolo());

        // Esegui direttamente l'aggiornamento senza gestire la transazione qui
        query.executeUpdate();
    }


    public void vidimaBiglietto(Biglietto b, Veicolo t) {
        if (b.getUtilizzabile() == null) {
            // Inizia la transazione qui, solo una volta
            em.getTransaction().begin();

            // La query per aggiornare il biglietto
            String sql = "UPDATE Biglietto b SET b.utilizzabile = CURRENT_DATE WHERE b.id = :id";
            Query query = em.createQuery(sql);
            query.setParameter("id", b.getId());

            // Esegui l'aggiornamento del biglietto
            query.executeUpdate();

            // Chiamata a increment senza una nuova transazione
            increment(t);

            // Commit della transazione
            em.getTransaction().commit();
        }
    }


    public void ciclo() {
TrattaDAO trattaDAO= new TrattaDAO(Utilities.em);
        boolean loop = true;
        long idTratta = trattaDAO.scegliTratta();
        while (loop) {
            long codiceBiglietto = Utilities.getLong("Inserisci codice biglietto");
            Utilities.sc.nextLine();
            Veicolo veicoloAssociato = getByID(idTratta); // Assumendo che idTratta corrisponda all'id del veicolo
            vidimaBiglietto((Biglietto) biglietto.getByID(codiceBiglietto), veicoloAssociato);
            System.out.println("1 per procedere con il viaggio, 2 per vidimare altri biglietti");
            String s = Utilities.sc.nextLine();

            if (s.equals("1")) {
                loop = false;
            } else if (!s.equals("1") && !s.equals("2")) {
                System.out.println("valore non valido");
            }
        }
    }




}
