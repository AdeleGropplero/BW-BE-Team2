package esami.epicode;

import esami.epicode.DAO.*;
import esami.epicode.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Scanner;


public class Main {
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("TrasportoPubblico_Team2");
    public static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        Console console = new Console();

        Scanner sc = new Scanner(System.in);

     //   MacchinettaDAO macchinettaDAO = new MacchinettaDAO(em);
     //   Rivenditore_autorizzatoDAO rivenditoreDAO = new Rivenditore_autorizzatoDAO(em);
        // TrattaDAO trattaDAO = new TrattaDAO(em);
       //  TesseraDAO tesseraUtente = new TesseraDAO(em);
       // VeicoloDAO veicoloDAO = new VeicoloDAO(em);
       // ViaggioDAO viaggioDAO = new ViaggioDAO(em);
        Titolo_di_viaggioDAO titoloDiViaggioDAO = new Titolo_di_viaggioDAO(em);

      //  macchinettaDAO.istanziaMacchinette(); //create quattro macchinette.
     //  rivenditoreDAO.istanziaRivenditore(); //creati quattro rivenditori.

        //  trattaDAO.istanziaTratta(); //create quattro tratte.

       //   tesseraUtente.istanziaTessereUtente(); //create 6 tessere

       //  veicoloDAO.istanziaVeicoli();

        //  viaggioDAO.istanziaViaggio(); //creati 4 viaggi

       //  titoloDiViaggioDAO.istanziaTitoloDiViaggio(); //creati 6 titoli di viaggio


//        Veicolo v = new Tram();
//        ParcoMezzi pm = new ParcoMezzi();

//        Periodo periodo1 = new Periodo(veicoloDAO.getByID(1), LocalDate.of(2024,2,1), LocalDate.of(2024,3,1));
//        PeriodoDAO periodoDao = new PeriodoDAO(em);
//        periodoDao.save(periodo1);
//        ParcoMezziDao pmd = new ParcoMezziDao(em);


          console.start();

//        pmd.saveParcoMezzi(pm);
//        pmd.saveVeicolo(v);


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