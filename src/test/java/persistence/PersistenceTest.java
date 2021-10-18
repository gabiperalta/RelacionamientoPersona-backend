package persistence;

import org.junit.Assert;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

public class PersistenceTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
    @Test
    public void contextUp(){
        Assert.assertNotNull(entityManager());
    }

    @Test
    public void contextUpWithTransaction(){
        withTransaction(()->{});
    }

    /*Si tira error respecto a la hora se debe ejecutar SET GLOBAL time_zone = '-3:00'; */
}
