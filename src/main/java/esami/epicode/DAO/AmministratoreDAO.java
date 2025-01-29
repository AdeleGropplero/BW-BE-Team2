package esami.epicode.DAO;

import esami.epicode.Entity.Amministratore;
import esami.epicode.entities.Tram;
import esami.epicode.entities.Tratta;
import esami.epicode.entities.Veicolo;

import javax.persistence.EntityManager;

public class AmministratoreDAO {
    EntityManager em;

    public Amministratore findById(long id) {
        return em.find(Amministratore.class, id);
    }

    public int tempoMedioTratta(Tratta t, Veicolo v){
        if(v instanceof Tram){
            t.getTempoPrevisto() + 5 + v.getNumBigliettiVidimati();
        }

    }


}
