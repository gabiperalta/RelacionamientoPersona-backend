package Negocio.Main;

import Negocio.Delegacion.Delegacion;
import Negocio.Plataforma.Plataforma;
import Negocio.ServicioPersona.APIPersona;
import Negocio.ServicioPersona.ListadoDePersonas;
import Negocio.ServicioPersona.Persona;
import Negocio.Usuario.Usuario;

import java.io.IOException;

public class TestService {
    public static void main(String[] args) throws IOException {
        ListadoDePersonas listadoDePersonas = APIPersona.getInstance().listadoDePersona();

//        System.out.println(listadoDePersonas.getPersonas().get(0).toString());
//        System.out.println(listadoDePersonas.getPersonas().get(1).toString());

        Usuario usuario = new Usuario("RickSanchez","11235811");
        Delegacion delegado = new Delegacion();
        Persona rick = new Persona(1,"Rick Sanchez", null, false, "Boedo","CABA",usuario);

        //plataforma.registrarUsuario(rick);
        //System.out.println(plataforma.listarUsuarios().get(0).toString());

        delegado.setAceptaDelegacion(true);
        rick.aprobar(delegado);
        rick.revocar(delegado);
    }
}
