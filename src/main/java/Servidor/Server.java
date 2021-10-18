package Servidor;

import Negocio.DB.EntityManagerHelper;
import Negocio.DB.PersonaPersistence;
import Negocio.Delegacion.Delegacion;
import Negocio.Delegacion.EstadoDelegacion;
import Negocio.Foto.Foto;
import Negocio.ServicioPersona.Persona;
import Negocio.Usuario.Usuario;
import spark.debug.DebugScreen;

import java.time.LocalDate;

import static spark.Spark.port;

public class Server {
    public static void main(String[] args) throws Exception {
        // se crea el entityManagerFactory
        EntityManagerHelper entityManagerHelper = new EntityManagerHelper();

        //port(9000);
        port(getHerokuAssignedPort());

        Router.init();
        //datosDePrueba();
        DebugScreen.enableDebugScreen();
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }

    public static void datosDePrueba(){

        Persona persona1 = new Persona();
        persona1.setName("Rick Sanchez");
        persona1.setEsAdministrador(false);
        persona1.setCiudad("CABA");
        persona1.setFechaNacimiento(LocalDate.of(1957,01,14));
        persona1.setLocalidad("CABA");
        persona1.setUsuario(new Usuario("rick","1234"));
        PersonaPersistence.create(persona1);

        Persona persona2 = new Persona();
        persona2.setName("Pepe Argento");
        persona2.setEsAdministrador(false);
        persona2.setCiudad("CABA");
        persona2.setLocalidad("CABA");
        persona2.setFechaNacimiento(LocalDate.of(1970,04,23));
        PersonaPersistence.create(persona2);

    }
}
