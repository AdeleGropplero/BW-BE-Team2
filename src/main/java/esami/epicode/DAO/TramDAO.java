package esami.epicode.DAO;

import esami.epicode.Entity.Biglietto;
import esami.epicode.entities.Periodo;
import esami.epicode.entities.Tram;
import esami.epicode.entities.Veicolo;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class TramDAO {

    EntityManager em ;

    public TramDAO (EntityManager em){
        this.em = em;
    }

    public Tram findById(long id){
        return em.find(Tram.class, id);
    }



}
