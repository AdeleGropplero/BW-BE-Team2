package esami.epicode;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;


public class Main 
{     public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("TrasportoPubblico_Team2");
    public static EntityManager em = emf.createEntityManager();
    public static void main( String[] args ) {


        Scanner sc = new Scanner(System.in);

        em.close();
        emf.close();
        //
    }
}
