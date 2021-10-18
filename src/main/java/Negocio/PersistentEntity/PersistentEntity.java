package Negocio.PersistentEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class PersistentEntity {
    @Id
    @GeneratedValue
    protected Long id;

    public Long getId() {
        return id;
    }
}
