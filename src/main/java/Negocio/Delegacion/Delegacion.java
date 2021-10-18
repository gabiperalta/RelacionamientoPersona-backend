package Negocio.Delegacion;

import Negocio.PersistentEntity.PersistentEntity;
import Negocio.ServicioPersona.Persona;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.uqbarproject.jpa.java8.extras.convert.LocalDateConverter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Delegacion extends PersistentEntity{
    @OneToOne(cascade = {CascadeType.PERSIST})
    Persona usuarioSolicitante;
    @OneToOne(cascade = {CascadeType.PERSIST})
    Persona usuarioAutorizado;
    @Enumerated(EnumType.STRING)
    EstadoDelegacion estadoDelegacion;
    @Convert(converter = LocalDateConverter.class)
    LocalDate horarioModificacionEstado;
    boolean aceptaDelegacion;

    public Delegacion(Persona usuarioSolicitante, Persona usuarioAutorizado){
        this.usuarioSolicitante = usuarioSolicitante;
        this.usuarioAutorizado = usuarioAutorizado;
        this.estadoDelegacion = EstadoDelegacion.EN_ESPERA;
        this.aceptaDelegacion = false;
    }

    public void setAceptaDelegacion(boolean aceptaDelegacion){
        this.aceptaDelegacion = aceptaDelegacion;
    }

    public boolean aceptarDelegacion(){
        if(aceptaDelegacion)
            estadoDelegacion = EstadoDelegacion.APROBADO;
        else
            estadoDelegacion = EstadoDelegacion.RECHAZADO;
        return aceptaDelegacion;
    }

    public void cambiarEstado(EstadoDelegacion estado){
        estadoDelegacion = estado;
    }

    public Persona getUsuarioSolicitante(){
        return usuarioSolicitante;
    }

    public Persona getUsuarioAutorizado(){
        return usuarioAutorizado;
    }
}
