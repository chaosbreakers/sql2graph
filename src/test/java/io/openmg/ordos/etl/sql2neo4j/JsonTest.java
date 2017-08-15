package io.openmg.ordos.etl.sql2neo4j;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.openmg.ordos.etl.sql2neo4j.entity.Entity;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * json test.
 * Created by zhaoliang on 2017/8/9.
 */
public class JsonTest {
//    @Test
    public void fromJson() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Entity entity = mapper.readValue(JsonTest.class.getClassLoader()
                .getResourceAsStream("sql2neo4j.json"), Entity.class);
        assertNotNull(entity);
        assertEquals("com.mysql.jdbc.Driver", entity.getMysql().getDriver());
        assertEquals(4, entity.getMysql().getTables().get(0).getColumns().size());

    }

    @Test
    public void test() throws Exception {
        String testString = "{\n" +
                "  \"graph.class\": \"io.graph.name\",\n" +
                "  \"name\": \"hello\",\n" +
                "  \"address\": \"CHANGSHA\"\n" +
                "}";
        ObjectMapper mapper = new ObjectMapper();


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

    class TestEntity {
        private HashMap<String, String> map;

        public HashMap<String, String> getMap() {
            return map;
        }

        public void setMap(HashMap<String, String> map) {
            this.map = map;
        }

        @Override
        public String toString() {
            return "TestEntity{" +
                    "map=" + map +
                    '}';
        }
    }
}
