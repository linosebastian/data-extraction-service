package com.healthcare.platform.solutions.processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthcare.platform.solutions.model.PGEntity;
import com.healthcare.platform.solutions.repository.PGRepository;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PGDataProcessor implements Processor {

    @Autowired
    private PGRepository pgRepository;

    @Override
    public void process(Exchange exchange) throws Exception {
        List<PGEntity> pgEntityList = pgRepository.findAll();
        if (pgEntityList.isEmpty()) {
            exchange.getIn().setBody(null);
        } else {
            exchange.getIn().setBody(convertPGEntityListToJson(pgEntityList));
        }
    }

    private String convertPGEntityListToJson(List<PGEntity> pgEntityList) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(pgEntityList);
    }
}
