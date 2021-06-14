package com.healthcare.platform.solutions.common;

public class PGConstants {

    public static final String DESTINATION_CSV = "src/main/resources/trial.csv";
    public static final String DATA_EXTRACTION_TRIGGER = "timer://hpsTimer?period=40000";

    public static final String BODY = "${body}";
    public static final String EXCEPTION = "${exception}";
    public static final String HPS_QUEUE = "{{hps.queue}}";
    public static final String ERROR_BODY = "Error Body : ";
    public static final String CSV_CONVERSION = "dataConversionToCSV";
    public static final String EXCEPTION_HANDLER = "direct:exceptionHandler";
    public static final String PG_DATA_PROCESS = "checkPGDataAvailabilityAndProcess";
    public static final String EXCEPTION_MESSAGE = "Exception occurred while processing,";
    public static final String PG_DATA_AVAILABILITY = "No data available from HPS_USER_DATA";
    public static final String CSV_CREATION_MESSAGE = "Successfully created CSV with PG HPS User data";
    public static final String DATA_SEND_TO_HPS_TRIAL_QUEUE = "Data pushed to HPS_TRIAL_DB_QUEUE for processing";
    public static final String DATA_RECEIVED_FROM_HPS_TRIAL_QUEUE = "Data consumed from HPS_TRIAL_DB_QUEUE for processing";
}
