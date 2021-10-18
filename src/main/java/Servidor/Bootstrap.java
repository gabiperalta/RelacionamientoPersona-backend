package Servidor;

import Negocio.Delegacion.Delegacion;
import Negocio.Delegacion.EstadoDelegacion;
import Negocio.Foto.Foto;
import Negocio.Plataforma.Plataforma;
import Negocio.ServicioPersona.Persona;
import Negocio.Usuario.Usuario;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.io.IOException;
import java.time.LocalDate;

public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {
    public static void main(String args []) {
        new Bootstrap().init();
    }

    public void init() {
        withTransaction(() -> {

            Foto foto1 = new Foto("https://www.google.com/url?sa=i&url=https%3A%2F%2Fsimpsons.fandom.com%2Fes%2Fwiki%2FHomer_Simpson&psig=AOvVaw2BFfBVpB_ZMzAa5FTUjFLP&ust=1634315410719000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCKDAir6pyvMCFQAAAAAdAAAAABAD");
            persist(foto1);
            Foto foto2 = new Foto("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pinterest.com%2Fpin%2F787637422319258976%2F&psig=AOvVaw2BFfBVpB_ZMzAa5FTUjFLP&ust=1634315410719000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCKDAir6pyvMCFQAAAAAdAAAAABAJ");
            persist(foto2);

            Persona persona1 = new Persona();
            persona1.setName("Rick Sanchez");
            persona1.setEsAdministrador(false);
            persona1.setCiudad("CABA");
            persona1.setFechaNacimiento(LocalDate.of(1957,01,14));
            persona1.setLocalidad("CABA");
            Usuario usuario1 = new Usuario("RickSanchez","11235811");
            persist(usuario1);
            persona1.setUsuario(usuario1);
            persona1.setFoto(foto1);
            persist(persona1);

            Persona persona2 = new Persona();
            persona2.setName("Pepe Argento");
            persona2.setEsAdministrador(false);
            persona2.setCiudad("CABA");
            persona2.setLocalidad("CABA");
            persona2.setFechaNacimiento(LocalDate.of(1970,04,23));
            Usuario usuario2 = new Usuario("PepeArgento","11235812");
            persist(usuario2);
            persona2.setUsuario(usuario2);
            persona2.setFoto(foto2);
            persist(persona2);

            Delegacion delegacion = new Delegacion(persona1,persona2, EstadoDelegacion.EN_ESPERA,LocalDate.now(),true);
            persist(delegacion);
            persist(Plataforma.getInstance());
        });
    }
}
