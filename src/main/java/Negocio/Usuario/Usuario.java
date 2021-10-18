package Negocio.Usuario;

import Negocio.PersistentEntity.PersistentEntity;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoDeUsuario")
@DiscriminatorValue("normal")
@NoArgsConstructor
public class Usuario extends PersistentEntity{
    String nombreUsuario;
    String clave;

    public Usuario(String nombreUsuario, String clave){
        this.nombreUsuario = nombreUsuario;
        this.clave = clave;
    }

    public boolean iniciarSesion(String nombreUsuario, String clave) {
        return this.clave.equals(clave) && this.nombreUsuario.equals(nombreUsuario);
    }

    public void cerrarSession(){

    }
}
