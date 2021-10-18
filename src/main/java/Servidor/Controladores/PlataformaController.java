package Servidor.Controladores;

import Negocio.DB.DelegacionPersistance;
import Negocio.DB.EntityManagerHelper;
import Negocio.DB.PersonaPersistence;
import Negocio.Delegacion.Delegacion;
import Negocio.Excepcion.PersonaException;
import Negocio.Excepcion.UsuarioException;
import Negocio.Plataforma.Plataforma;
import Negocio.ServicioPersona.Persona;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlataformaController {

    public String obtenerPersonas(Request request, Response response){
        List<Persona> listaDePersonas = PersonaPersistence.getAll();
        return new Gson().toJson(listaDePersonas);
    }

    public String obtenerPersonaPorId(Request request,Response response){
        String id = request.params("id");
        return new Gson().toJson(PersonaPersistence.findById(Integer.parseInt(id)));
    }

    public String obtenerDelegaciones(Request request, Response response) throws IOException {
        String idUsuarioSolicitante = request.params("id_usuario_solicitante");
        String idUsuarioAutorizado = request.params("id_usuario_autorizado");

        List<Delegacion> listaDeDelegaciones = new ArrayList<>();

        if(idUsuarioSolicitante != null)
            listaDeDelegaciones = DelegacionPersistance.getByUsuarioSolicitante(Integer.parseInt(idUsuarioSolicitante));
        else if(idUsuarioAutorizado != null)
            listaDeDelegaciones = DelegacionPersistance.getByUsuarioAutorizado(Integer.parseInt(idUsuarioAutorizado));
        else{
            listaDeDelegaciones = DelegacionPersistance.getAll();
        }

        return new Gson().toJson(listaDeDelegaciones);
    }

    public Object registrarUsuario(Request request,Response response) throws IOException {
        String id = request.queryParams("id");
        String nombreUsuario = request.queryParams("nombre_usuario");
        String clave = request.queryParams("clave");

        JsonObject respuestaJson = new JsonObject();

        try{
            Plataforma.getInstance().registrarUsuario(Integer.parseInt(id),nombreUsuario,clave);
        }catch (PersonaException e){
            response.status(400); // TODO: revisar status code
            respuestaJson.addProperty("mensaje","Persona con id " + id + " no se encuentra habilitado");
            return respuestaJson;
        }

        EntityManagerHelper.commit();

        response.status(200);
        respuestaJson.addProperty("mensaje","Usuario creado con exito");
        return respuestaJson;
    }
}
