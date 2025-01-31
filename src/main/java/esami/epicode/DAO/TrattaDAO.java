package esami.epicode.DAO;

import esami.epicode.Entity.PuntoVendita;
import esami.epicode.Utilities.Utilities;
import esami.epicode.entities.Tratta;
import esami.epicode.entities.Viaggio;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class TrattaDAO {
    private EntityManager em;

    VeicoloDAO veicoloDAO=new VeicoloDAO(em);


    Tratta t1 = new Tratta("Colosseo", "Piazza Cavour", 15, veicoloDAO.getByID(1));
    Tratta t2 = new Tratta("Piazza Mirti", "Piramide", 35,veicoloDAO.getByID(2));
    Tratta t3 = new Tratta("Vaticano", "Ponte Mammolo", 25,veicoloDAO.getByID(3));
    Tratta t4 = new Tratta("Prati", "Parioli", 20,veicoloDAO.getByID(4));

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
    public List<Tratta> findAll(){
        Query q= em.createQuery("SELECT t FROM Tratta t");
        return q.getResultList();

    }
    public long scegliTratta(){
        System.out.println("Dove vuoi andare?");
       List<Tratta> tratte=findAll();
        for (int i = 0; i < tratte.size(); i++) {
            System.out.println("premi " + (i+1) +" Per " + tratte.get(i).getPartenza() + " - " + tratte.get(i).getCapolinea());
        }
        int scelta=Utilities.sc.nextInt();
        Utilities.sc.nextLine();

        Viaggio v=new Viaggio(tratte.get(scelta).getVeicolo(),tratte.get(scelta));

        tratte.get(scelta).getVeicolo().getViaggi().add(v);

        return tratte.get(scelta).getId();

    }


}
