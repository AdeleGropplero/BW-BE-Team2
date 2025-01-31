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
    TrattaDAO tratta = new TrattaDAO(em);


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
        String sql = "UPDATE Tram b SET b.numBigliettiVidimati + 1 WHERE b.id = :id";

        em.getTransaction().begin();

        Query query = em.createQuery(sql);

        query.setParameter("id", t.getCodiceVeicolo());

        em.getTransaction().commit();
    }

    public void vidimaBiglietto(Biglietto b, Veicolo t) {
        if (b.getUtilizzabile() == null) {
            String sql = "UPDATE Biglietto b SET b.utilizzabile = CURRENT_DATE WHERE b.id = :id";

            em.getTransaction().begin();

            Query query = em.createQuery(sql);

            query.setParameter("id", b.getId());

            increment(t);

            em.getTransaction().commit();
        }

    }

    public void ciclo() {

        boolean loop = true;
        long idTratta = tratta.scegliTratta();
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
