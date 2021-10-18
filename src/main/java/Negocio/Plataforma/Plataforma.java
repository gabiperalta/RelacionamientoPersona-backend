package Negocio.Plataforma;

import Negocio.DB.PersonaPersistence;
import Negocio.Delegacion.Delegacion;
import Negocio.Excepcion.PersonaException;
import Negocio.Excepcion.UsuarioException;
import Negocio.PersistentEntity.PersistentEntity;
import Negocio.ServicioPersona.APIPersona;
import Negocio.ServicioPersona.ListadoDePersonas;
import Negocio.ServicioPersona.Persona;
import Negocio.Usuario.Usuario;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Plataforma extends PersistentEntity{
    @OneToMany(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "plataforma_id")
    List<Persona> personas;
    @OneToMany(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "plataforma_id")
    List<Delegacion> delegaciones;
    @Transient
    ListadoDePersonas listadoDePersonas;
    private static Plataforma instancia;

    private Plataforma(){
        try{
            listadoDePersonas = APIPersona.getInstance().listadoDePersona();
        }catch (IOException e){
            listadoDePersonas = null;
        }
        personas = new ArrayList<Persona>();
        delegaciones = new ArrayList<Delegacion>();
    }

    public static Plataforma getInstance(){
        if(instancia == null)
            instancia = new Plataforma();
        return instancia;
    }

    public void registrarUsuario(int id,String nombreUsuario, String clave){
        List<Persona> personasActual = PersonaPersistence.getAll();

        if(personasActual.stream().noneMatch(p->p.getId() == id)) {
            throw new PersonaException("Persona con id " + id + " no se encuentra habilitado");
        }
        else{
            Persona personaEncontrada = personasActual.stream().filter(persona -> persona.getId() == id).collect(Collectors.toList()).get(0);
            personaEncontrada.setUsuario(new Usuario(nombreUsuario,clave));
        }
    }

    public List<Persona> listarPersonas(){
        return personas;
    }

    public List<Delegacion> listarDelegaciones(){
        return delegaciones;
    }

    public List<Delegacion> listarDelegacionesPorUsuarioSolicitante(int id){
        return delegaciones.stream().filter(delegacion -> delegacion.getUsuarioSolicitante().getId() == id).collect(Collectors.toList());
    }

    public List<Delegacion> listarDelegacionesPorUsuarioAutorizado(int id){
        return delegaciones.stream().filter(delegacion -> delegacion.getUsuarioAutorizado().getId() == id).collect(Collectors.toList());
    }
}
