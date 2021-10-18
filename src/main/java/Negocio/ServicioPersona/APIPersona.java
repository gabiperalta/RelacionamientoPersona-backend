package Negocio.ServicioPersona;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class APIPersona {
    static APIPersona instancia = null;
    static final String urlAPI = "https://rickandmortyapi.com/api/";
    private Retrofit retrofit;

    private APIPersona(){
        this.retrofit = new Retrofit.Builder().baseUrl(urlAPI)
                .addConverterFactory(GsonConverterFactory.create()).build();

    }

    public static APIPersona getInstance(){
        if(instancia == null)
            instancia = new APIPersona();
        return instancia;
    }

    public ListadoDePersonas listadoDePersona() throws IOException {
        IListadoPersona iListadoPersona = this.retrofit.create(IListadoPersona.class);
        Call<ListadoDePersonas> requestListadoPersonas = iListadoPersona.personas();
        Response<ListadoDePersonas> responseListadoPersonas = requestListadoPersonas.execute();
        return responseListadoPersonas.body();
    }
}
