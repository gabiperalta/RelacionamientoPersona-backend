package Negocio.DB;

import Negocio.Delegacion.Delegacion;
import Negocio.ServicioPersona.Persona;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class DelegacionPersistance {
    public static void create(Delegacion delegacion){
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(delegacion);
        EntityManagerHelper.commit();
    }

    public static void delete(int id){
        Delegacion delegacion = findById(id);
        EntityManagerHelper.beginTransaction();
        //Quedo muy largo el parametro
        EntityManagerHelper.getEntityManager().remove(EntityManagerHelper.getEntityManager().contains(delegacion)?delegacion:EntityManagerHelper.getEntityManager().merge(delegacion));
        EntityManagerHelper.commit();
    }

    public static Delegacion findById(int id){
        EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
        return (Delegacion) entityManager.find(Delegacion.class, id);
    }

    public static List<Delegacion> getAll(){
        return EntityManagerHelper.createQuery("from Delegacion").getResultList();
    }

    public static List<Delegacion> getByUsuarioSolicitante(int idPersona){
        Query query = EntityManagerHelper.createQuery("from Delegacion d " +
                "where d.usuarioSolicitante.id = :idPersona");
        query.setParameter("idPersona",idPersona);
        return  (List<Delegacion>) query.getResultList();
    }

    public static List<Delegacion> getByUsuarioAutorizado(int idPersona){
        Query query = EntityManagerHelper.createQuery("from Delegacion d " +
                "where d.usuarioAutorizado.id = :idPersona");
        query.setParameter("idPersona",idPersona);
        return  (List<Delegacion>) query.getResultList();
    }
}
