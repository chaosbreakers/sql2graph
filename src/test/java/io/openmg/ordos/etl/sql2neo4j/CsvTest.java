package io.openmg.ordos.etl.sql2neo4j;

import au.com.bytecode.opencsv.CSVReader;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * csv reader test.
 * Created by zhaoliang on 2017/8/16.
 */
public class CsvTest {

    @Test
    public void read() throws Exception {
        CSVReader csvReader = new CSVReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("test.csv")));
        List<String[]> strings = csvReader.readAll();
        String[] strings1 = strings.get(0);
        Assert.assertEquals("zhaoliang", strings1[0]);
        Assert.assertEquals("23", strings1[1]);
        Assert.assertEquals("weston", strings1[2]);
        Assert.assertEquals("changsha", strings1[3]);
        csvReader.close();
    }

    @Test
    public void readLoop() throws Exception {
        CSVReader csvReader = new CSVReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("test.csv")));
        String[] temp;
        while ((temp = csvReader.readNext()) != null) {
            Assert.assertEquals("[zhaoliang, 23, weston, changsha]", Arrays.toString(temp));
        }
        csvReader.close();
    }
}
