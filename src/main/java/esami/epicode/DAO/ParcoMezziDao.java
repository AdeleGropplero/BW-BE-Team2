package esami.epicode.DAO;

import esami.epicode.Entity.Biglietto;
import esami.epicode.entities.ParcoMezzi;
import esami.epicode.entities.Periodo;
import esami.epicode.entities.Veicolo;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;

public class ParcoMezziDao {

    private EntityManager em;
    ParcoMezzi pm = new ParcoMezzi();

    public ParcoMezziDao(EntityManager em){
        this.em = em;
    }

    public void saveParcoMezzi(ParcoMezzi v){
        em.getTransaction().begin();
        em.persist(v);
        em.getTransaction().commit();
    }

    public ParcoMezzi getParcoMezziById (long id){
        return em.find(ParcoMezzi.class, id);
    }

    public void deleteParcoMezzi(ParcoMezzi v){
        em.getTransaction().begin();
        em.remove(v);
        em.getTransaction().commit();
    }

    public void saveVeicolo(Veicolo v){
        em.getTransaction().begin();
        em.persist(v);
        em.getTransaction().commit();
    }

    public Veicolo getById (long codVeicolo){
        return em.find(Veicolo.class, codVeicolo);
    }

    public void deleteVeicolo(Veicolo v){
        em.getTransaction().begin();
        em.remove(v);
        em.getTransaction().commit();
    }








}
