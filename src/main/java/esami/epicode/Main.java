package esami.epicode;

import esami.epicode.DAO.ParcoMezziDao;
import esami.epicode.entities.ParcoMezzi;
import esami.epicode.entities.Tram;
import esami.epicode.entities.Veicolo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Main 
{     public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("TrasportoPubblico_Team2");
    public static EntityManager em = emf.createEntityManager();
    public static void main( String[] args ) {

        Veicolo v = new Tram();
        ParcoMezzi pm = new ParcoMezzi();

        ParcoMezziDao pmd = new ParcoMezziDao(em);

        pmd.saveParcoMezzi(pm);
        pmd.saveVeicolo(v);

        em.close();
        emf.close();
        //
    }
}
