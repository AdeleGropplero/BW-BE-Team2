package esami.epicode.entities;

import esami.epicode.DAO.AmministratoreDAO;
import esami.epicode.DAO.PuntoVenditaDAO;
import esami.epicode.DAO.TesseraDAO;
import esami.epicode.DAO.UtenteDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Console {

    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("TrasportoPubblico_Team2");
    public static EntityManager em = emf.createEntityManager();

Scanner sc = new Scanner(System.in);
UtenteDAO ud = new UtenteDAO(em);
TesseraDAO td = new TesseraDAO(em);
PuntoVenditaDAO pd = new PuntoVenditaDAO(em);
AmministratoreDAO ad = new AmministratoreDAO(em);

private String text = "Digita 1 per passare alla modalità amministratore \n" +
        "Digita 2 se sei un utente \n" +
        "Digita 3 per Viaggiare";


    public void start (){
        String choice = "";
        choice = sc.nextLine();

        switch(choice){

            case "1":
                ad.gestioneAmministratore();
                break;

            case "2":
                // Utente
                pd.operazioniUtente();
                break;

            case "3":
                //Tratta
                System.out.println();
                break;


        }


    }
}
