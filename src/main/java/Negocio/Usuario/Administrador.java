package Negocio.Usuario;

import Negocio.Delegacion.Delegacion;
import Negocio.Plataforma.Plataforma;
import Negocio.ServicioPersona.Persona;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("administrador")
public class Administrador extends Usuario {
    public Administrador(String nombreUsuario,String clave){
        super(nombreUsuario, clave);
    }

    public List<Persona> listarPersonas(Plataforma plataforma){
        return plataforma.listarPersonas();
    }
    public List<Delegacion> listarDelegacion(Plataforma plataforma){
        return plataforma.listarDelegaciones();
    }
}
