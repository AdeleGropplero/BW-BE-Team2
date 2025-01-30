package esami.epicode.DAO;

import esami.epicode.Entity.Amministratore;
import esami.epicode.Entity.Biglietto;
import esami.epicode.Utilities.Utilities;
import esami.epicode.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
    public static long codice_veicolo;

    public AmministratoreDAO(EntityManager em) {
        this.em = em;
    }

    public Amministratore findById(long id) {
        return em.find(Amministratore.class, id);
    }

    public int calcolaMediaTempoPercorrenzaTratta() {
        tuttiIVeicoli();
        System.out.println("Seleziona il codice del veicolo che vuoi controllare:");
        codice_veicolo = sc.nextLong();
        Query q = em.createNamedQuery("getVeicolo");
        q.setParameter("codice_veicolo", codice_veicolo);
        Veicolo v = (Veicolo) q.getSingleResult();

        int sommaTempiEffettivi = 0;
        for (Viaggio viaggio : v.getViaggi()) {
            sommaTempiEffettivi += viaggio.getTempoEffettivo();
        }
        return sommaTempiEffettivi / v.getViaggi().size();
    }

    public void numeroPercorrenzaTratta() {
        //long numero = v.getViaggi().stream().filter(viaggio -> viaggio.getVeicolo().equals(v) && viaggio.getTratta().equals(t)).count();
        tuttiIVeicoli();
        System.out.println("Seleziona il codice del veicolo che vuoi controllare:");
        codice_veicolo = sc.nextLong();
        Query q = em.createNamedQuery("getVeicolo");
        q.setParameter("codice_veicolo", codice_veicolo);
        Veicolo v = (Veicolo) q.getSingleResult();

        System.out.println("Il veicolo " + v.getCodiceVeicolo() + " ha percorso " + v.getViaggi().size() + " volte la tratta " + v.getViaggi().get(0).getTratta().getPartenza() + " - " + v.getViaggi().get(0).getTratta().getCapolinea());

        /*  System.out.println("Il numero di volte che il veicolo " + v.getCodiceVeicolo() + " ha percorso la tratta con partenza a: " + v.().get(0).getPartenza() +
                " e capolinea a: " + v.getTratta().get(0).getCapolinea() + " è: " + v.getTratta().size());*/
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

    public void tuttiIVeicoli() {
        Query q = em.createQuery("SELECT v FROM Veicolo v");
        List<Veicolo> veicoli = (List<Veicolo>) q.getResultList();
        veicoli.forEach(System.out::println);
    }


    public void getPeriodiManutenzione() {
        tuttiIVeicoli();
        System.out.println("Seleziona il codice del veicolo che vuoi controllare:");
        codice_veicolo = sc.nextLong();

        Query q = em.createNamedQuery("getPeriodoManutenzione");
        q.setParameter("codice_veicolo", codice_veicolo);
        System.out.println("I veicoli in manutenzione sono: " + q.getResultList());
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

    public void totaleBigliettiVidimatiPerVeicolo() {
        tuttiIVeicoli();
        System.out.println("Seleziona il codice del veicolo che vuoi controllare:");

        codice_veicolo = sc.nextLong();

        Query q = em.createNamedQuery("getVeicolo");
        q.setParameter("codice_veicolo", codice_veicolo);
        Veicolo v = (Veicolo) q.getSingleResult();
        System.out.println("Il numero di biglietti vidimati è: " + v.getNumBigliettiVidimati());
    }

    public void totaleBigliettiVidimatiPerPeriodo() {

        System.out.println("Scrivi la data di inizio del controllo nel formato GG/MM/AAAA");

        String data = sc.nextLine();

        LocalDate dataForm = LocalDate.parse(data, formatter);

        System.out.println("Scrivi la data di fine del controllo nel formato GG/MM/AAAA");

        String data2 = sc.nextLine();

        LocalDate dataForm2 = LocalDate.parse(data2, formatter);

        String sql = "SELECT b FROM Biglietto b WHERE b.utilizzabile = null AND b.utilizzabile BETWEEN dataForm AND dataForm2";

        em.getTransaction().begin();

        TypedQuery<Biglietto> query = em.createQuery(sql, Biglietto.class);

        query.setParameter("dataForm", dataForm);
        query.setParameter("dataForm2", dataForm2);

        List<Biglietto> biglietti = query.getResultList();

        em.getTransaction().commit();

        System.out.println(biglietti.size());
    }

    public void gestioneAmministratore() {
        boolean x = true;
        boolean y = true;
        String sceltaUtente;
        while (x) {
            System.out.println("Seleziona -1- per controllare i periodi di manutenzione di un veicolo");
            System.out.println("Seleziona -2- per controllare il numero di biglietti vidimati su un mezzo");
            System.out.println("Seleziona -3- per controllare il numero di biglietti vidimati su un mezzo in un determinato periodo di tempo");
            System.out.println("Seleziona -4- per controllare il numero di volte che un mezzo percorre una tratta");
            System.out.println("Seleziona -5- per controllare il tempo medio effettivo di percorrenza di una tratta da parte di un mezzo");

            sceltaUtente = Utilities.sc.nextLine();

            switch (sceltaUtente) {
                case "1":
                    getPeriodiManutenzione();
                    break;
                case "2":
                    totaleBigliettiVidimatiPerVeicolo();
                    break;
                case "3":
                    totaleBigliettiVidimatiPerPeriodo();
                    break;
                case "4":
                    numeroPercorrenzaTratta();
                    break;
                case "5":
                    calcolaMediaTempoPercorrenzaTratta();
                    break;
                default:
                    System.out.println("Per favore inserici un valore valido");
            }

            while(y) {
                System.out.println("Vuoi continuare i controlli? y/n");
                sceltaUtente = Utilities.sc.nextLine();
                if (sceltaUtente.equals("n")) {
                    x = false;
                    y = false;
                    System.out.println("Arrivederci!");
                } else if (sceltaUtente.equals("y")) {
                    y = false;
                } else {
                    System.out.println("Per favore inserisci un valore valido");
                }
            }

        }
    }
}