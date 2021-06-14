package com.healthcare.platform.solutions.processor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

import static com.healthcare.platform.solutions.common.PGConstants.DESTINATION_CSV;

@Component
public class CSVDataProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        convertToCSV(exchange.getIn().getBody(String.class));
    }

    private void convertToCSV(String consumedFromQueue) throws IOException {
        JsonNode jsonTree = new ObjectMapper().readTree(consumedFromQueue);
        CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
        JsonNode firstObject = jsonTree.elements().next();
        firstObject.fieldNames().forEachRemaining(csvSchemaBuilder::addColumn);
        CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        csvMapper.writerFor(JsonNode.class)
                .with(csvSchema)
                .writeValue(new File(DESTINATION_CSV), jsonTree);
    }
}
