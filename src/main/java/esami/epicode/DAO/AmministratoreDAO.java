package esami.epicode.DAO;

import esami.epicode.Entity.Amministratore;
import esami.epicode.Entity.Biglietto;
import esami.epicode.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class AmministratoreDAO {
    EntityManager em;
    Scanner sc = new Scanner(System.in);
    ParcoMezzi pm = new ParcoMezzi();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public AmministratoreDAO(EntityManager em) {
        this.em = em;
    }

    public Amministratore findById(long id) {
        return em.find(Amministratore.class, id);
    }

    public int calcolaMediaTempoPercorrenzaTratta(Veicolo v) {
        int sommaTempiEffettivi = 0;
        for (Tratta tratta : v.getTratta()) {
            sommaTempiEffettivi += tratta.getTempoEffettivo();
        }
        return sommaTempiEffettivi / v.getTratta().size();
    }

    public void numeroPercorrenzaTratta(Veicolo v) {
        System.out.println("Il numero di volte che il veicolo " + v + " ha percorso la tratta con partenza a: " + v.getTratta().get(0).getPartenza() +
                " e capolinea a: " + v.getTratta().get(0).getCapolinea() + " è: " + v.getTratta().size());
    }

    // METODI PER PERIODO DI MANUTENZIONE MEZZO

    public void savePeriodo(Periodo p) {
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }

    public void leftManutenzione(LocalDate dataFine, long codiceVeicolo) {
        String sql = "UPDATE Periodo p SET p.dataFine = :dataFine WHERE p.veicolo.codiceVeicolo = :codiceVeicolo";

        em.getTransaction().begin();

        TypedQuery<Periodo> query = em.createQuery(sql, Periodo.class);

        query.setParameter("dataFine", dataFine);
        query.setParameter("codiceVeicolo", codiceVeicolo);
        em.getTransaction().commit();
    }

    public void putManutenzione(Veicolo a) {
        Periodo p = new Periodo();
        p.setDataInizio(LocalDate.now());
        savePeriodo(p);
        a.getPeriodi().add(p);
        pm.getPeriodiVeicoli().add(a);


    }

    public void updateIncremento() {
        String sql = "UPDATE Biglietto b SET b.utilizzabile = false WHERE b.id = :id";
    }

    public void vidimaBiglietto(Biglietto b) {
        String sql = "UPDATE Biglietto b SET b.utilizzabile = CURRENT_DATE WHERE b.id = :id";

        em.getTransaction().begin();

        TypedQuery<Biglietto> query = em.createQuery(sql, Biglietto.class);

        query.setParameter("id", b.getId());

        em.getTransaction().commit();
    }

    public int totaleBigliettiVidimati() {

        System.out.println("Scrivi la data di inizio del controllo nel formato GG/MM/AAAA");

        String data = sc.nextLine();

        LocalDate dataForm = LocalDate.parse(data, formatter);

        System.out.println("Scrivi la data di fine del controllo nel formato GG/MM/AAAA");

        String data2 = sc.nextLine();

        LocalDate dataForm2 = LocalDate.parse(data2, formatter);

        String sql = "SELECT Biglietto b FROM Biglietto WHERE b.utilizzabile = false AND b.utilizzabile BETWEEN dataForm AND dataForm2";

        em.getTransaction().begin();

        TypedQuery<Biglietto> query = em.createQuery(sql, Biglietto.class);

        query.setParameter("dataForm", dataForm);
        query.setParameter("dataForm2", dataForm2);

        List<Biglietto> biglietti = query.getResultList();

        em.getTransaction().commit();

        return biglietti.size();
    }
}
