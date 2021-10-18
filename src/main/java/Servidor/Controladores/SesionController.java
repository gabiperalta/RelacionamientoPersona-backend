package Servidor.Controladores;

import Negocio.DB.PersonaPersistence;
import Negocio.ServicioPersona.Persona;
import Negocio.Usuario.Usuario;
import com.google.gson.JsonObject;
import spark.Request;
import spark.Response;

public class SesionController {
    public Object login(Request request, Response response){
        String nombreUsuario = request.queryParams("nombre_usuario");
        String clave = request.queryParams("clave");

        if(request.queryParams("remember-credentials") != null){
            response.cookie("nombre_usuario", nombreUsuario);
            response.cookie("clave", clave);
            response.cookie("remember-credentials-checked", "true");
        }
        else{
            response.removeCookie("nombre_usuario");
            response.removeCookie("clave");
            response.removeCookie("remember-credentials-checked");
        }

        Persona personaEncontrada = PersonaPersistence.findByNombreUsuario(nombreUsuario);
        Usuario usuarioEncontrado = personaEncontrada.getUsuario();
        if(usuarioEncontrado != null){
            if(usuarioEncontrado.iniciarSesion(nombreUsuario,clave)) {
                request.session(true);
                request.session().attribute("usuario", usuarioEncontrado);
                request.session().attribute("persona", personaEncontrada);
                response.removeCookie("CredencialesNoValidas");
                response.removeCookie("CuentaBloqueada");
            }
            else {
                response.cookie("CredencialesNoValidas", "true");
            }
        }
        else{
            response.cookie("CredencialesNoValidas", "true");
        }

        response.status(302);

        JsonObject respuestaJson = new JsonObject();
        respuestaJson.addProperty("mensaje","La sesion inicio correctamente");

        return respuestaJson;
    }

    public Object logout(Request request, Response response){
        request.session().removeAttribute("user");

        JsonObject respuestaJson = new JsonObject();
        respuestaJson.addProperty("mensaje","La sesion finalizo correctamente");

        return respuestaJson;
    }
}
