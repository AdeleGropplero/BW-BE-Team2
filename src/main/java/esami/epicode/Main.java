package esami.epicode;

import esami.epicode.DAO.*;
import esami.epicode.entities.ParcoMezzi;
import esami.epicode.entities.Tram;
import esami.epicode.entities.Veicolo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Scanner;


public class Main 
{     public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("TrasportoPubblico_Team2");
    public static EntityManager em = emf.createEntityManager();
    public static void main( String[] args ) {


        Scanner sc = new Scanner(System.in);

/*
        MacchinettaDAO macchinettaDAO = new MacchinettaDAO(em);
        macchinettaDAO.istanziaMacchinette(); //create quattro macchinette.

        Rivenditore_autorizzatoDAO rivenditoreDAO = new Rivenditore_autorizzatoDAO(em);
        rivenditoreDAO.istanziaRivenditore(); //creati quattro rivenditori.

        TrattaDAO trattaDAO = new TrattaDAO(em);
        trattaDAO.istanziaTratta(); //create quattro tratte.

        TesseraDAO tesseraUtente = new TesseraDAO(em);
        tesseraUtente.istanziaTessereUtente(); //create 6 tessere


        VeicoloDAO veicoloDAO = new VeicoloDAO(em);
        veicoloDAO.istanziaVeicoli();

        ViaggioDAO viaggioDAO = new ViaggioDAO(em);
        viaggioDAO.istanziaViaggio(); //creati 4 viaggi

        Titolo_di_viaggioDAO titoloDiViaggioDAO = new Titolo_di_viaggioDAO(em);
        titoloDiViaggioDAO.istanziaTitoloDiViaggio(); //creati 6 titoli di viaggio
*/


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
/*
        TramDAO tramDAO = new TramDAO(em);
        tramDAO.istanziaTram(); //creati 3 tram

        AutobusDAO autobus = new AutobusDAO(em);
        autobus.istanziaAutobus(); //creati 3 bus
*/