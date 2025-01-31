package esami.epicode.DAO;

import esami.epicode.Entity.Amministratore;
import esami.epicode.Entity.Biglietto;
import esami.epicode.Exception.NessunVeicoloTrovato;
import esami.epicode.Exception.NessunaTrattaException;
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
    //    ParcoMezzi pm = new ParcoMezzi();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static long codice_veicolo;

    public AmministratoreDAO(EntityManager em) {
        this.em = em;
    }

    public Amministratore findById(long id) {
        return em.find(Amministratore.class, id);
    }

    public void calcolaMediaTempoPercorrenzaTratta() {
        tuttiIVeicoli();
        System.out.println("Seleziona il codice del veicolo che vuoi controllare:");
        codice_veicolo = sc.nextLong();
        sc.nextLine();
        Query q = em.createNamedQuery("getVeicolo", Veicolo.class);
        q.setParameter("codice_veicolo", codice_veicolo);
        Veicolo v = (Veicolo) q.getSingleResult();

        if (!v.getViaggi().isEmpty()) {
            int sommaTempiEffettivi = 0;
            for (Viaggio viaggio : v.getViaggi()) {
                sommaTempiEffettivi += viaggio.getTempoEffettivo();
            }
            System.out.println("Il tempo medio di viaggio è: " + (sommaTempiEffettivi / v.getViaggi().size()) + " minuti");
        } else {
            throw new NessunaTrattaException("Il veicolo selezionato al momento non ha percorso nessuna tratta");
        }
    }

    public void numeroPercorrenzaTratta() {
        //long numero = v.getViaggi().stream().filter(viaggio -> viaggio.getVeicolo().equals(v) && viaggio.getTratta().equals(t)).count();
        tuttiIVeicoli();
        System.out.println("Seleziona il codice del veicolo che vuoi controllare:");
        codice_veicolo = sc.nextLong();
        sc.nextLine();


        Query q = em.createNamedQuery("getVeicolo", Veicolo.class);
        q.setParameter("codice_veicolo", codice_veicolo);
        List<Veicolo> veicoli = (List<Veicolo>) q.getResultList();
        if (!veicoli.isEmpty()) {
            if (!veicoli.get(0).getViaggi().isEmpty()) {

                System.out.println("Il veicolo " + veicoli.get(0).getCodiceVeicolo() + " ha percorso " + veicoli.get(0).getViaggi().size() + " volte la tratta " + veicoli.get(0).getViaggi().get(0).getTratta().getPartenza() + " - " + veicoli.get(0).getViaggi().get(0).getTratta().getCapolinea());
            } else {
                throw new NessunaTrattaException("Il veicolo selezionato al momento non ha percorso nessuna tratta");
            }

        } else {
            throw new NessunVeicoloTrovato("Il codice inserito non corrisponde a nessun veicolo");
        }
    }

    // METODI PER PERIODO DI MANUTENZIONE MEZZO

    public void savePeriodo(Periodo p) {
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }

//    public void leftManutenzione(LocalDate dataFine, long codiceVeicolo) {
//        String sql = "UPDATE Periodo p SET p.dataFine = :dataFine WHERE p.veicolo.codiceVeicolo = :codiceVeicolo";
//        em.getTransaction().begin();
//        Query query = em.createQuery(sql);
//
//        query.setParameter("dataFine", dataFine);
//        query.setParameter("codiceVeicolo", codiceVeicolo);
//        query.executeUpdate();
//        em.getTransaction().commit();
//    }

    public void putManutenzione(Veicolo a) {
        Periodo p = new Periodo(a, (LocalDate.now()), LocalDate.now().plusDays(10));
        savePeriodo(p);
        a.getPeriodi().add(p);
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
        sc.nextLine();

        Query q = em.createNamedQuery("getPeriodoManutenzione", Periodo.class);
        q.setParameter("codice_veicolo", codice_veicolo);
        List<Veicolo> veicoli = (List<Veicolo>) q.getResultList();
        if (!veicoli.isEmpty()) {
            System.out.println("I veicoli in manutenzione sono: " + veicoli);
        } else {
            throw new NessunVeicoloTrovato("Il codice inserito non corrisponde a nessun veicolo");
        }

    }


    public void updateIncremento() {
        String sql = "UPDATE Biglietto b SET b.utilizzabile = CURRENT_DATE WHERE b.id = :id";
    }


    public void vidimaBiglietto(Biglietto b) {
        String sql = "UPDATE Biglietto b SET b.utilizzabile = CURRENT_DATE WHERE b.id = :id";

        em.getTransaction().begin();

        Query query = em.createQuery(sql);

        query.setParameter("id", b.getId());

        em.getTransaction().commit();
    }

    public void totaleBigliettiVidimatiPerVeicolo() {
        tuttiIVeicoli();
        System.out.println("Seleziona il codice del veicolo che vuoi controllare:");

        codice_veicolo = sc.nextLong();
        sc.nextLine();

        Query q = em.createNamedQuery("getVeicolo");
        q.setParameter("codice_veicolo", codice_veicolo);

        List<Veicolo> veicoli = (List<Veicolo>) q.getResultList();
        if (!veicoli.isEmpty()) {
            System.out.println("Il numero di biglietti vidimati è: " + veicoli.get(0).getNumBigliettiVidimati());
        } else {
            throw new NessunVeicoloTrovato("Il codice inserito non corrisponde a nessun veicolo");
        }
    }

    public String giornoInizio;
    public String meseInizio;
    public String annoInizio;

    public String giornoFine;
    public String meseFine;
    public String annoFine;

    public void totaleBigliettiVidimatiPerPeriodo() {

        System.out.println("Scrivi il giorno di inizio del controllo: ");
        giornoInizio = sc.nextLine();
        System.out.println("Scrivi il mese di inizio del controllo: ");
        meseInizio = sc.nextLine();
        System.out.println("Scrivi l'anno di inizio del controllo: ");
        annoInizio = sc.nextLine();

        LocalDate dataInizio = LocalDate.of(Integer.parseInt(annoInizio), Integer.parseInt(meseInizio), Integer.parseInt(giornoInizio));

        System.out.println("Scrivi il giorno di fine del controllo: ");
        giornoFine = sc.nextLine();
        System.out.println("Scrivi il mese di fine del controllo: ");
        meseFine = sc.nextLine();
        System.out.println("Scrivi l'anno di fine del controllo: ");
        annoFine = sc.nextLine();

        LocalDate dataFine = LocalDate.of(Integer.parseInt(annoFine), Integer.parseInt(meseFine), Integer.parseInt(giornoFine));

        String sql = "SELECT b FROM Biglietto b WHERE b.utilizzabile BETWEEN :dataInizio AND :dataFine";

        em.getTransaction().begin();

        TypedQuery<Biglietto> query = em.createQuery(sql, Biglietto.class);

        query.setParameter("dataInizio", dataInizio);
        query.setParameter("dataFine", dataFine);

        List<Biglietto> biglietti = query.getResultList();

        em.getTransaction().commit();

        System.out.println("I biglietti vidimati nel periodo selezionato sono: " + biglietti.size());
    }

    public void gestioneAmministratore() {
        boolean x = true;
        boolean y = true;
        String sceltaUtente;
        while (x) {
            System.out.println("Seleziona -1- per controllare i periodi di manutenzione di un veicolo");
            System.out.println("Seleziona -2- per controllare il numero di biglietti vidimati su un mezzo");
            System.out.println("Seleziona -3- per controllare il numero di biglietti vidimati in un determinato periodo di tempo");
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
                    try {
                        try {
                            numeroPercorrenzaTratta();
                        } catch (NessunaTrattaException e) {
                            System.out.println(e.getMessage());
                        }
                    } catch (NessunVeicoloTrovato e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "5":
                    try {
                        calcolaMediaTempoPercorrenzaTratta();
                        ;
                    } catch (NessunaTrattaException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                default:
                    System.out.println("Per favore inserici un valore valido");
            }

            while (y) {
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