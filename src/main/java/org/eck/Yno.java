package org.eck;

import java.util.HashMap;
import java.util.Map;

import org.eck.exceptions.NoCollectorRegistredException;
import org.sql2o.Sql2o;

public class Yno {
    private Sql2o sql2o;

    @SuppressWarnings("rawtypes")
    private Map<Class, Collector> collectors = new HashMap<Class, Collector>();

    public Yno(String url, String user, String password) {
        this.sql2o = new Sql2o(url, user, password);
    }

    @SuppressWarnings("rawtypes")
    public void registerCollector(Class clazz, Collector collector) {
        collectors.put(clazz, collector);
    }

    public Inserter insert(Object obj) {
        Collector collector = collectors.get(obj.getClass());
        if(collector == null) {
            throw new NoCollectorRegistredException();
        }
        return new Inserter(collector, sql2o, obj);
    }

    public Updater update(Object obj) {
        Collector collector = collectors.get(obj.getClass());
        if(collector == null) {
            throw new NoCollectorRegistredException();
        }
        return new Updater(collector, sql2o, obj);
    }
}
