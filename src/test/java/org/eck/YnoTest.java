package org.eck;

import org.junit.Assert;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class YnoTest {

    @Test
    public void test() {
        String url = "jdbc:postgresql://localhost:5432/ynotest";
        String user = "postgres";
        String password = "postgres";
        Yno yno = new Yno(url, user, password);
        yno.registerCollector(Bar.class, (obj, collectedData) -> {
            collectedData.table("Bar");

            Bar bar = (Bar) obj;
            collectedData.data("foo", bar.getFoo());
        });

        Bar bar = new Bar();
        bar.setFoo("Foo");
        Long key = yno.insert(bar).insertAndFetchKey();
        Assert.assertNotNull(key);

        bar.setFoo("Fooooo");
        yno.update(bar).where("id", key).execute();

        String sql = "SELECT id, foo FROM Bar WHERE id= :id";
        Sql2o sql2o = new Sql2o(url, user, password);
        try (Connection con = sql2o.open()) {
            bar = con.createQuery(sql).addParameter("id", key)
                    .executeAndFetchFirst(Bar.class);
            Assert.assertEquals("Fooooo", bar.getFoo());
        }
    }
}
