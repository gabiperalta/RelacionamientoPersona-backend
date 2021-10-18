package Servidor;

import Servidor.Controladores.*;
import Servidor.Utils.BooleanHelper;
import Servidor.Utils.HandlebarsTemplateEngineBuilder;
import spark.Spark;
import spark.Spark.*;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.post;
import static spark.Spark.get;

public class Router {
    private static HandlebarsTemplateEngine engine;

    public static void init() throws Exception {
        Router.initEngine();

        boolean localhost = false;
        if(localhost){
            String projectDir = System.getProperty("user.dir");
            String staticDir = "/src/main/resources/public";
            Spark.externalStaticFileLocation(projectDir + staticDir);
        }
        else{
            Spark.staticFileLocation("/public");
        }

        Router.configure();
    }

    private static void initEngine() {
        Router.engine = HandlebarsTemplateEngineBuilder
                .create()
                .withDefaultHelpers()
                .withHelper("isTrue", BooleanHelper.isTrue)
                .build();
    }


    public static void configure() throws Exception {
        UsuarioController usuarioController = new UsuarioController();
        PlataformaController plataformaController = new PlataformaController();
        SesionController sesionController = new SesionController();
        PersonaController personaController = new PersonaController();
        DelegacionController delegacionController = new DelegacionController();

        get("/hello",(req,res) -> "Hola mundo!");

        post("/login",sesionController::login);
        post("/logout",sesionController::logout);

        get("/personas",plataformaController::obtenerPersonas);
        get("/persona",plataformaController::obtenerPersonaPorId);
        post("/persona",personaController::actualizarDatos);

        get("/delegaciones",plataformaController::obtenerDelegaciones);
        post("/delegacion/crear",delegacionController::crearDelegacion);
        post("/delegacion/aceptar",delegacionController::aceptarDelegacion);
        post("/delegacion/revocar",delegacionController::rechazarDelegacion);

        post("/usuario/registrar",plataformaController::registrarUsuario);

    }
}
