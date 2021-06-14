package com.healthcare.platform.solutions.resources;

import com.healthcare.platform.solutions.processor.CSVDataProcessor;
import com.healthcare.platform.solutions.processor.PGDataProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.healthcare.platform.solutions.common.PGConstants.*;

@Component
public class ExtractionComponent extends RouteBuilder {

    @Autowired
    private PGDataProcessor pgDataProcessor;

    @Autowired
    private CSVDataProcessor csvDataProcessor;

    @Override
    public void configure() throws Exception {

        onException(Exception.class).handled(true).to(EXCEPTION_HANDLER);

        from(DATA_EXTRACTION_TRIGGER)
                .routeId(PG_DATA_PROCESS)
                .process(pgDataProcessor)
                .choice()
                .when(body().isNotNull()).to(HPS_QUEUE).otherwise()
                .log(PG_DATA_AVAILABILITY)
                .end();

        from(HPS_QUEUE)
                .log(DATA_RECEIVED_FROM_HPS_TRIAL_QUEUE)
                .routeId(CSV_CONVERSION)
                .process(csvDataProcessor)
                .log(CSV_CREATION_MESSAGE)
                .end();

        from(EXCEPTION_HANDLER)
                .log(ERROR_BODY + BODY)
                .log(EXCEPTION_MESSAGE + EXCEPTION);
    }
}
