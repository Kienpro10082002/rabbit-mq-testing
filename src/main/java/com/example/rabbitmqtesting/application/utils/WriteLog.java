package com.example.rabbitmqtesting.application.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WriteLog {

    private static final Logger LOG = LoggerFactory.getLogger(WriteLog.class);

    public static void debug(String message) { LOG.debug(message); }

    public static void error(Object object, Throwable e) { LOG.error(object.toString(), e); }

    public static void error(Throwable e) { LOG.error(e.toString(), e); }

    public static void error(Object transId, Object object, Throwable e) {
        error("[" + transId + "]---" + object, e);
    }

    public static void error(Object transId, Object object, String process, String message, Throwable e) {
        error(String.format("[%s][%s][%s]", transId, object, process), message, e);
    }

    public static void write(Object object) { LOG.info(object.toString()); }

    public static void write(Object transId, Object object) {
        String log = object+"";
        try {
            log = log.replaceAll("\\b\\d{16}\\b", "<<card_number>>");
        } catch (Exception e) { e.printStackTrace(); }

        write("[" + transId + "]---" + log);
    }

}
