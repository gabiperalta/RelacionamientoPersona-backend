package Servidor.Controladores;

import Negocio.DB.PersonaPersistence;
import Negocio.Foto.Foto;
import Negocio.ServicioPersona.Persona;
import com.google.gson.JsonObject;
import spark.Request;
import spark.Response;

import java.time.LocalDate;

public class PersonaController {

    public Object actualizarDatos(Request request, Response response){
        String id = request.queryParams("id");
        String fechaNacimientoDia = request.queryParams("fecha_nacimiento_dia");
        String fechaNacimientoMes = request.queryParams("fecha_nacimiento_mes");
        String fechaNacimientoAnio = request.queryParams("fecha_nacimiento_anio");
        String ciudad = request.queryParams("ciudad");
        String localidad = request.queryParams("localidad");
        String fotoRutaArchivo = request.queryParams("foto");

        Persona persona = PersonaPersistence.findById(Integer.parseInt(id));

        if(fechaNacimientoAnio!=null && fechaNacimientoMes!=null && fechaNacimientoDia!=null){
            persona.setFechaNacimiento(LocalDate.of(Integer.parseInt(fechaNacimientoAnio),Integer.parseInt(fechaNacimientoMes),Integer.parseInt(fechaNacimientoDia)));
        }

        if(ciudad!=null){
            persona.setCiudad(ciudad);
        }

        if(localidad!=null){
            persona.setLocalidad(localidad);
        }

        if(fotoRutaArchivo!=null){
            persona.setFoto(new Foto(fotoRutaArchivo));
        }

        JsonObject respuestaJson = new JsonObject();
        respuestaJson.addProperty("mensaje","Persona actualizada con exito");
        return respuestaJson;
    }
}
