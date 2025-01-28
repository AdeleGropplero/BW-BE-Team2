package esami.epicode.DAO;

import esami.epicode.Entity.Abbonamento;
import esami.epicode.Entity.PuntoVendita;
import esami.epicode.Entity.Rivenditore_autorizzato;
import esami.epicode.Entity.Tessera;
import esami.epicode.Enum.Cadenza;
import esami.epicode.Exception.tesseraNonEsistenteException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;

public class AbbonamentoDAO {
    private EntityManager em;

    public AbbonamentoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(PuntoVendita e) {
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }

    public PuntoVendita getByID(long id) {
        return em.find(PuntoVendita.class, id);
    }

    public void delete(PuntoVendita e) {
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
    }

    public void verificaValiditaAbbonamento(long nTessera) {
        Query q = em.createQuery("SELECT a FROM Abbonamento a WHERE a.tessera.id = :nTessera ");
        q.setParameter("nTessera", nTessera);
        Abbonamento abbonamento = (Abbonamento) q.getSingleResult();
        if (abbonamento.getData_di_scadenza().isAfter(LocalDate.now())) {
            System.out.println("L'abbonamento è ancora VALIDO, scade il: " + abbonamento.getData_di_scadenza());

        }else {
            System.out.println("L'abbonamento è SCADUTO il: " + abbonamento.getData_di_scadenza());
            //chiama metodo rinnovo 🟧  // Da capire come gestire
            System.out.println("Recati in un punto vendita per acquistare un nuovo abbonamento");
        }
    }

    public void acquistoAbbonamento(java.util.Scanner sc, Rivenditore_autorizzato r_id){
        System.out.println("Acquisto abbonamento : Inserisci il tuo numero di tessera: ");
        Query q = em.createQuery("SELECT t FROM Tessera t WHERE t.id = :nTessera");
        q.setParameter("nTessera",sc.nextLine());
        Tessera tessera = (Tessera) q.getSingleResult();
        if(tessera != null){
            if(tessera.getScadenza().isAfter(LocalDate.now())){
                System.out.println("Selezionare 1 per abbonamento SETTIMANALE :");
                System.out.println("Selezionare 2 per abbonamento MENSILE :");
                int tipologiaAbbonamento = sc.nextInt();
                sc.nextLine();
                // Implementare scelta cadenza abbonamento 🟥 rivedere dopo la pausa relazione abbonamento tessera
                switch (tipologiaAbbonamento){
                    case 1:
                        System.out.println("Hai scelto un abbonamento SETTIMANALE!");
                        Cadenza cadenzaSettimanale = Cadenza.SETTIMANALE;
                        Abbonamento a = new Abbonamento(LocalDate.now(),r_id,cadenzaSettimanale);
                        break;
                    case 2:
                        System.out.println("Hai scelto un abbonamento MENSILE!");
                        Cadenza cadenzaMensile = Cadenza.MENSILE;
                        Abbonamento b = new Abbonamento(LocalDate.now(),r_id,cadenzaMensile);
                        break;
                    default:
                        System.out.println("Per favore inserisci un valore valido");

                }


            }else{
                System.out.println("La tua tessera è scaduta, seleziona 1 per rinnovare, seleziona 2 per uscire! ");
                int sceltaUtente = sc.nextInt();
                sc.nextLine();
                switch (sceltaUtente){
                    case 1:
                        tessera.setData_attivazione(LocalDate.now());
                        System.out.println("Tessera rinnovata con scadenza il giorno :" + LocalDate.now().plusDays(365));
                        break;
                    case 2:
                        System.out.println("Chiudo...");
                        sc.close();
                        return;
                    default:
                        System.out.println("Per favore inserisci un valore valido");

                }
            }
        }else{
             throw new tesseraNonEsistenteException("Non risulta una tessera valida, per favore creane una nella sezione dedicata");

        }
    }
}
