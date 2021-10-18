package Negocio.Main;

import Negocio.DB.EntityManagerHelper;
import Negocio.DB.PersonaPersistence;
import Negocio.Foto.Foto;
import Negocio.ServicioPersona.Persona;
import Negocio.Usuario.Usuario;

import java.time.LocalDate;
import java.util.List;

public class TestDB {
    public static void main(String[] args) {
//        Usuario usuario = new Usuario("HomeroSimpson","12345678");
//        Persona persona = new Persona();
//        persona.setName("Homero Simpson");
//        persona.setEsAdministrador(false);
//        persona.setCiudad("CABA");
//        persona.setFechaNacimiento(LocalDate.of(1957,01,14));
//        persona.setLocalidad("CABA");
//        persona.setUsuario(usuario);
//        PersonaPersistence.create(persona);

//        System.out.println(PersonaPersistence.findById(1).toString());

//        List<Persona> personas = PersonaPersistence.getAll();
//        for(Persona p: personas)
//            System.out.println(p.toString());

        // PersonaPersistence.delete(5);
        Persona persona = PersonaPersistence.findById(3);
        EntityManagerHelper.beginTransaction();
        persona.setName("Homero Jay Simpson");
        EntityManagerHelper.getEntityManager().merge(persona);
        EntityManagerHelper.commit();

    }
}
