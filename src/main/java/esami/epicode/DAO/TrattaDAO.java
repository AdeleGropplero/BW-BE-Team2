package esami.epicode.DAO;

import esami.epicode.Entity.PuntoVendita;
import esami.epicode.Utilities.Utilities;
import esami.epicode.entities.Tratta;
import esami.epicode.entities.Veicolo;
import esami.epicode.entities.Viaggio;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class TrattaDAO {
    private final EntityManager em;




    Tratta t1 = new Tratta("Colosseo", "Piazza Cavour", 15);
    Tratta t2 = new Tratta("Piazza Mirti", "Piramide", 35);
    Tratta t3 = new Tratta("Vaticano", "Ponte Mammolo", 25);
    Tratta t4 = new Tratta("Prati", "Parioli", 20);

    public TrattaDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Tratta e) {
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }

    public Tratta getByID(long id) {
        return em.find(Tratta.class, id);
    }

    public void delete(Tratta e) {
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
    }

    public void istanziaTratta() {
        save(t1);
        save(t2);
        save(t3);
        save(t4);
    }

    public List<Tratta> findAll() {
        Query q = em.createQuery("SELECT t FROM Tratta t");
        return q.getResultList();
    }

    public Long scegliTratta() {
        System.out.println("Dove vuoi andare?");
        List<Tratta> tratte = findAll();

        // Mostra le tratte
        for (int i = 0; i < tratte.size(); i++) {
            System.out.println("Premi " + (i + 1) + " per " + tratte.get(i).getPartenza() + " - " + tratte.get(i).getCapolinea());
        }
        System.out.println("Oppure digita 0 per uscire");

        int scelta = Utilities.sc.nextInt();
        Utilities.sc.nextLine();

        // Verifica che la scelta sia valida
        if (scelta == 0) {
            System.out.println("Chiudo...");
            Utilities.sc.close();
            return null;
        }  else if (scelta < 1 || scelta > tratte.size()) {
            System.out.println("Scelta non valida.");
            return scegliTratta(); // Riprova se l'input non è valido
        }

        // Recupera la tratta selezionata
        Tratta trattaSelezionata = tratte.get(scelta - 1);  // Il -1 è per allineare l'indice (scelta inizia da 1)

        // Recupera il veicolo associato alla tratta selezionata
        Veicolo veicoloAssociato = veicoloAssociatoPerTratta(scelta - 1);  // Il -1 allinea la scelta con gli indici dei veicoli

        // Crea il viaggio con il veicolo associato
        Viaggio viaggio = new Viaggio(veicoloAssociato, trattaSelezionata);

        // Output per confermare il viaggio creato
        System.out.println("Viaggio creato con veicolo: " + veicoloAssociato.getCodiceVeicolo() + " per la tratta: " + trattaSelezionata.getPartenza() + " - " + trattaSelezionata.getCapolinea());

        // Restituisce l'ID della tratta selezionata
        return trattaSelezionata.getId();
    }

    public Veicolo veicoloAssociatoPerTratta(int scelta) {
        VeicoloDAO veicoloDAO=new VeicoloDAO(Utilities.em);
        // Restituisce il veicolo associato alla tratta selezionata
        switch (scelta) {
            case 0: return veicoloDAO.getByID(1);  // Prima tratta, primo veicolo
            case 1: return veicoloDAO.getByID(2);  // Seconda tratta, secondo veicolo
            case 2: return veicoloDAO.getByID(3);  // Terza tratta, terzo veicolo
            case 3: return veicoloDAO.getByID(4);  // Quarta tratta, quarto veicolo
            default: throw new IllegalArgumentException("Tratta non valida");
        }
    }




    /* Viaggio v = new Viaggio(veicolo, tratte.get(scelta));

     tratte.get(scelta).getVeicolo().getViaggi().add(v);

     return tratte.get(scelta).getId();
*/
    public List<Veicolo> veicoliAssociatiAtratta() {
        Query q = Utilities.em.createQuery("SELECT v FROM Veicolo v WHERE v.codiceVeicolo BETWEEN 1 AND 4");
        return q.getResultList();
    }
}



/*    public void inizioVid(){
        veicoloDAO.ciclo();

    }*/


