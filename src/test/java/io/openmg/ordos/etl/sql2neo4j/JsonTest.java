package io.openmg.ordos.etl.sql2neo4j;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.openmg.ordos.etl.sql2neo4j.entity.Entity;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * json test.
 * Created by zhaoliang on 2017/8/9.
 */
public class JsonTest {
    //    @Test
    private static final ObjectMapper mapper = new ObjectMapper();

    public void fromJson() throws Exception {
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

        Map<String, String> graph = mapper.readValue(testString, new TypeReference<Map<String, String>>() {
        });

        List<String> keys = Arrays.asList("graph.class", "name", "address");
        List<String> values = Arrays.asList("io.graph.name", "hello", "CHANGSHA");
        graph.keySet().forEach(key -> assertTrue(keys.contains(key)));
        graph.keySet().stream().map(graph::get).forEach(value -> assertTrue(values.contains(value)));
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
