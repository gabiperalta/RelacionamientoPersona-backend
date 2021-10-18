package Servidor.Controladores;

import Negocio.DB.DelegacionPersistance;
import Negocio.DB.PersonaPersistence;
import Negocio.Delegacion.Delegacion;
import Negocio.Delegacion.EstadoDelegacion;
import Negocio.ServicioPersona.Persona;
import com.google.gson.JsonObject;
import spark.Request;
import spark.Response;

public class DelegacionController {

    public Object crearDelegacion(Request request, Response response){
        String idUsuarioSolicitante = request.queryParams("id_usuario_solicitante");
        String idUsuarioAutorizado = request.queryParams("id_usuario_autorizado");

        Persona personaSolicitante = PersonaPersistence.findById(Integer.parseInt(idUsuarioSolicitante));
        Persona personaAutorizada = PersonaPersistence.findById(Integer.parseInt(idUsuarioAutorizado));

        Delegacion delegacion = new Delegacion(personaSolicitante,personaAutorizada);
        DelegacionPersistance.create(delegacion);

        JsonObject respuestaJson = new JsonObject();
        respuestaJson.addProperty("mensaje","Delegacion creada correctamente");
        return respuestaJson;
    }

    public Object aceptarDelegacion(Request request, Response response){
        String idDelegacion = request.queryParams("id_delegacion");

        Delegacion delegacion = DelegacionPersistance.findById(Integer.parseInt(idDelegacion));
        delegacion.cambiarEstado(EstadoDelegacion.APROBADO);

        JsonObject respuestaJson = new JsonObject();
        respuestaJson.addProperty("mensaje","Delegacion aceptada");
        return respuestaJson;
    }

    public Object rechazarDelegacion(Request request, Response response){
        String idDelegacion = request.queryParams("id_delegacion");

        Delegacion delegacion = DelegacionPersistance.findById(Integer.parseInt(idDelegacion));
        delegacion.cambiarEstado(EstadoDelegacion.RECHAZADO);

        JsonObject respuestaJson = new JsonObject();
        respuestaJson.addProperty("mensaje","Delegacion aceptada");
        return respuestaJson;
    }
}
