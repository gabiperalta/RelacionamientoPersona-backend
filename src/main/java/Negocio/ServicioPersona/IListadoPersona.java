package Negocio.ServicioPersona;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IListadoPersona {
    @GET("character")
    Call<ListadoDePersonas> personas();
}
