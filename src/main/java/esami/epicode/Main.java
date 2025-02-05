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
//

//        MacchinettaDAO macchinettaDAO = new MacchinettaDAO(em);
//        macchinettaDAO.istanziaMacchinette(); //create quattro macchinette.
//
//        Rivenditore_autorizzatoDAO rivenditoreDAO = new Rivenditore_autorizzatoDAO(em);
//        rivenditoreDAO.istanziaRivenditore(); //creati quattro rivenditori.
//
//        TrattaDAO trattaDAO = new TrattaDAO(em);
//        trattaDAO.istanziaTratta(); //create quattro tratte.
//
//        TesseraDAO tesseraUtente = new TesseraDAO(em);
//        tesseraUtente.istanziaTessereUtente(); //create 6 tessere
//
//
        VeicoloDAO veicoloDAO = new VeicoloDAO(em);
        //  veicoloDAO.istanziaVeicoli();
//
//        ViaggioDAO viaggioDAO = new ViaggioDAO(em);
//        viaggioDAO.istanziaViaggio(); //creati 4 viaggi
//
//        Titolo_di_viaggioDAO titoloDiViaggioDAO = new Titolo_di_viaggioDAO(em);
//        titoloDiViaggioDAO.istanziaTitoloDiViaggio(); //creati 6 titoli di viaggio

        console.start();


//        Veicolo v = new Tram();
//        ParcoMezzi pm = new ParcoMezzi();

//        Periodo periodo1 = new Periodo(veicoloDAO.getByID(1), LocalDate.of(2024,2,1), LocalDate.of(2024,3,1));
//        PeriodoDAO periodoDao = new PeriodoDAO(em);
//        periodoDao.save(periodo1);
//        ParcoMezziDao pmd = new ParcoMezziDao(em);


//        pmd.saveParcoMezzi(pm);
//        pmd.saveVeicolo(v);

        AmministratoreDAO amministratoreDao = new AmministratoreDAO(em);

/*
        amministratoreDao.putManutenzione(veicoloDAO.getByID(17));
        amministratoreDao.putManutenzione(veicoloDAO.getByID(9));
        amministratoreDao.putManutenzione(veicoloDAO.getByID(18));
*/


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