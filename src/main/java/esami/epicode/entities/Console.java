package esami.epicode.entities;

import esami.epicode.DAO.ParcoMezziDAO;
import esami.epicode.DAO.TesseraDAO;
import esami.epicode.DAO.UtenteDAO;
import esami.epicode.DAO.VeicoloDAO;
import esami.epicode.Utilities.Utilities;

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
VeicoloDAO vd = new VeicoloDAO(em);

private String text = "Digita 1 per scegliere se comprare biglietto o abbonamento /n" +
        "digita 2 per registrare una tessera /n" +
        "digita 3 per scegliere la tratta da percorrere /n" +
        "digita 4 per  /n" +
        "digita 5 per validare il biglietto o mostrare l'abbonamento";

    public void start (){
        String choice = "";
        choice = sc.nextLine();

        switch(choice){

            case "1":
                ud.createUtente();
                break;

            case "2":
                td.createTessera();
                break;

            case "7":

        }


    }

    public void administrator (ParcoMezziDAO pmd){
        String choice = " ";

        choice = sc.nextLine();

        switch(choice){
            case "1":
                long id = Utilities.getLong("Inserisci l'id del veicolo");
                pmd.putManutenzione(vd.findById(id));
        }
    }
}
