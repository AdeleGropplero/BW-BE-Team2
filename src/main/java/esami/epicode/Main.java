package esami.epicode;

import esami.epicode.DAO.MacchinettaDAO;
import esami.epicode.DAO.ParcoMezziDao;
import esami.epicode.DAO.Rivenditore_autorizzatoDAO;
import esami.epicode.DAO.TrattaDAO;
import esami.epicode.entities.ParcoMezzi;
import esami.epicode.entities.Tram;
import esami.epicode.entities.Veicolo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;


public class Main 
{     public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("TrasportoPubblico_Team2");
    public static EntityManager em = emf.createEntityManager();
    public static void main( String[] args ) {


        Scanner sc = new Scanner(System.in);

        MacchinettaDAO macchinettaDAO = new MacchinettaDAO(em);
        macchinettaDAO.istanziaMacchinette(); //create quattro macchinette.

        Rivenditore_autorizzatoDAO rivenditoreDAO = new Rivenditore_autorizzatoDAO(em);
        rivenditoreDAO.istanziaRivenditore(); //creati quattro rivenditori.

        TrattaDAO trattaDAO = new TrattaDAO(em);
        trattaDAO.istanziaTratta(); //create quattro tratte.

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
