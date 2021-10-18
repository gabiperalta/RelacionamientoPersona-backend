package Negocio.DB;

import Negocio.ServicioPersona.Persona;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class PersonaPersistence {

    public static void create(Persona persona){
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(persona);
        EntityManagerHelper.commit();
    }

    //Esto va a depender que quiero cambiar
    public static void update(int id){
        Persona persona = findById(id);
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().merge(persona);
        EntityManagerHelper.commit();
    }

    public static void delete(int id){
        Persona persona = findById(id);
        EntityManagerHelper.beginTransaction();
        //Quedo muy largo el parametro
        EntityManagerHelper.getEntityManager().remove(EntityManagerHelper.getEntityManager().contains(persona)?persona:EntityManagerHelper.getEntityManager().merge(persona));
        EntityManagerHelper.commit();
    }

    public static Persona findById(int id){
        EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
        return (Persona) entityManager.find(Persona.class, id);
    }

    public static List<Persona> getAll(){
        return EntityManagerHelper.createQuery("from Persona").getResultList();
    }

    public static Persona findByNombreUsuario(String nombreUsuario){
        Query query = EntityManagerHelper.createQuery("from Persona p " +
                "where p.usuario.nombreUsuario = :nombreUsuario");
        query.setParameter("nombreUsuario",nombreUsuario);
        Persona persona = (Persona) query.getResultList().get(0);
        return persona;
    }
}
