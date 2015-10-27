package kz.enu.fit.dao;

import java.util.List;
import kz.enu.fit.log.Log;
import kz.enu.fit.entities.Entity;

public abstract class AbstractDAO<T extends Entity> {

    private static Log logger = new Log();

    /**
     * abstract, parametric method that will find the necessary objects from the
     * database, and returns a collection object
     *
     * @return
     */
    public abstract List<T> findAll();

}
