package esami.epicode.entities;

import esami.epicode.DAO.AmministratoreDAO;
import esami.epicode.DAO.PuntoVenditaDAO;
import esami.epicode.DAO.TesseraDAO;
import esami.epicode.DAO.VeicoloDAO;
//import esami.epicode.DAO.UtenteDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Console {

    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("TrasportoPubblico_Team2");
    public static EntityManager em = emf.createEntityManager();

    Scanner sc = new Scanner(System.in);
    //UtenteDAO ud = new UtenteDAO(em);
    TesseraDAO td = new TesseraDAO(em);
    PuntoVenditaDAO pd = new PuntoVenditaDAO(em);
    AmministratoreDAO ad = new AmministratoreDAO(em);
    VeicoloDAO vd = new VeicoloDAO(em);

    private String text = "Digita 1 per passare alla modalità amministratore \n" +
            "Digita 2 se sei un utente \n" +
            "Digita 3 per Viaggiare";


    public void start() {
        System.out.println(text);
        String choice = "";
        choice = sc.nextLine();
        while (true) {
            switch (choice) {

                case "1":
                    ad.gestioneAmministratore();
                    break;

                case "2":
                    // Utente
                   boolean continua = true;
                    while (continua){
                    pd.operazioniUtente();
                        System.out.println("Vuoi continuare con gli acquisti? y/n");
                        choice = sc.nextLine();
                        if (choice.equals("n")){
                            continua = false;
                        }
                    }
                    vd.ciclo();
                    break;

                case "3":
                    //Tratta
                    vd.ciclo();
                    System.out.println();
                    break;


            }
        }

    }
}
