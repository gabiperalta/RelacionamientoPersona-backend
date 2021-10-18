package Negocio.ServicioPersona;

import Negocio.Delegacion.Delegacion;
import Negocio.Delegacion.EstadoDelegacion;
import Negocio.Foto.Foto;
import Negocio.Plataforma.Plataforma;
import Negocio.Usuario.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.uqbarproject.jpa.java8.extras.convert.LocalDateConverter;

import javax.persistence.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Persona {
    @Id
    @GeneratedValue
    int id; //uso id como DNI
    String name;
    @Convert(converter = LocalDateConverter.class)
    LocalDate fechaNacimiento;
    boolean esAdministrador;
    String ciudad;
    String localidad;
    @OneToOne(cascade = {CascadeType.PERSIST})
    Foto foto;
    @OneToOne(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    Usuario usuario;

    public Persona(int id, String name){
        this.id = id;
        this.name = name;
    }

    public Persona(int id, String name, LocalDate fechaNacimiento, boolean esAdministrador, String ciudad, String localidad, Usuario usuario){
        this.id = id;
        this.name = name;
        this.fechaNacimiento = fechaNacimiento;
        this.esAdministrador = esAdministrador;
        this.ciudad = ciudad;
        this.localidad = localidad;
        this.foto = new Foto();
        this.usuario = usuario;
    }

    public void esparar(Delegacion delegacion){
        delegacion.cambiarEstado(EstadoDelegacion.EN_ESPERA);
    }

    public void aprobar(Delegacion delegacion){
        delegacion.cambiarEstado(EstadoDelegacion.APROBADO);
    }

    public void revocar(Delegacion delegacion){
        delegacion.cambiarEstado(EstadoDelegacion.RECHAZADO);
    }

    @Override
    public String toString(){
        return "DNI: "+id + "\tNombre y Apellido: " + name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setEsAdministrador(boolean esAdministrador) {
        this.esAdministrador = esAdministrador;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario(){
        return usuario;
    }
}
