package Negocio.Foto;

import Negocio.PersistentEntity.PersistentEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
public class Foto extends PersistentEntity {
    String rutaArchivo;

    public Foto(String rutaArchivo){
        this.rutaArchivo = rutaArchivo;
    }

    public Foto() {

    }
}
