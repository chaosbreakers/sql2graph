package io.openmg.ordos.etl.sql2neo4j;

import au.com.bytecode.opencsv.CSVReader;
import io.openmg.ordos.etl.sql2neo4j.entity.csv.CSVSchema;
import io.openmg.ordos.etl.sql2neo4j.entity.csv.Column;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoliang on 2017/8/16.
 */
public class Csv2Graph implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Csv2Graph.class);

    List<CSVSchema> csv;
    Graph graph;

    public Csv2Graph(List<CSVSchema> csv, Graph graph) {
        this.csv = csv;
        this.graph = graph;
    }

    @Override
    public void run() {
        for (CSVSchema csvSchema : csv) {
            CSVReader csvReader = null;
            try {
                try {
                    csvReader = new CSVReader(new FileReader(new File(csvSchema.getPath())));
                } catch (FileNotFoundException e) {
                    logger.error(e.getMessage(), e);
                }
                String[] temp = null;
                try {
                    while ((temp = csvReader.readNext()) != null) {
                        write2graph(csvSchema, temp);
                    }
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            } finally {
                try {
                    csvReader.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }

        }
    }


    /**
     * put one line data of csv file to graph according to {@linkplain CSVSchema}
     *
     * @param schema csv schema.
     * @param data   one line data in csv file.
     */
    public void write2graph(CSVSchema schema, String[] data) {
        List<Column> columns = schema.getColumns();
        ArrayList<Object> objects = new ArrayList<>();
        columns.stream().forEach(column -> {
            int pos = column.getPos();
            String name = column.getName();
            String valueType = column.getValueType();
            String datum = data[pos];
            Object value = null;
            switch (valueType) {
                case "string":
                    value = datum;
                    break;
                case "int":
                    value = Integer.valueOf(datum);
                    break;
                case "long":
                    value = Long.valueOf(datum);
                    break;
                default:
                    value = datum;
            }
            objects.add(value);
            objects.add(name);
        });
        objects.add(T.label);
        objects.add(schema.getLabel());

        logger.info("write data {} to graph.", objects);
        graph.addVertex(objects);
        graph.tx().commit();
    }
}
