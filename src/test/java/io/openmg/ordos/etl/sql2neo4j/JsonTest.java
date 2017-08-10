package io.openmg.ordos.etl.sql2neo4j;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.openmg.ordos.etl.sql2neo4j.entity.Entity;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * json test.
 * Created by zhaoliang on 2017/8/9.
 */
public class JsonTest {
    @Test
    public void fromJson() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Entity entity = mapper.readValue(JsonTest.class.getClassLoader()
                .getResourceAsStream("sql2neo4j.json"), Entity.class);
        assertNotNull(entity);
        assertEquals("com.mysql.jdbc.Driver", entity.getMysql().getDriver());
        assertEquals(4, entity.getMysql().getTable().getColumns().size());

    }

    @Test
    public void stringJoin() throws Exception {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("i");
        strings.add("love");
        strings.add("you");

        String join = String.join(",", strings);
        String expected = "i,love,you";
        assertEquals(expected, join);
    }
}
