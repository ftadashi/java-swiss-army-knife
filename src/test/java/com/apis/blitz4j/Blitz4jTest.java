package com.apis.blitz4j;

import com.netflix.blitz4j.LoggingConfiguration;
import com.netflix.servo.DefaultMonitorRegistry;
import com.netflix.servo.monitor.CompositeMonitor;
import com.netflix.servo.monitor.Monitor;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class Blitz4jTest {

    private static Properties props = new Properties();
    private static final String consoleSummarizeEvent = "stdout_summarizeEvent";
    private static final String consoleEventsProcessed = "stdout_putInBuffer";

    @BeforeClass
    public static void setUp() {

        props.setProperty("log4j.rootCategory", "OFF");
        props.setProperty("log4j.logger.com.apis.blitz4j.Blitz4jTest", "INFO,stdout");
//        props.setProperty("log4j.logger.com.apis.blitz4j.Blitz4jTest$1", "INFO,stdout");
        props.setProperty("log4j.appender.stdout", "org.apache.log4j.ConsoleAppender");
        props.setProperty("log4j.appender.stdout.layout", "com.netflix.logging.log4jAdapter.NFPatternLayout");
        props.setProperty("log4j.appender.stdout.layout.ConversionPattern", "%d %-5p %C:%L [%t] [%M] %m%n");
        props.setProperty("log4j.logger.asyncAppenders", "INFO,stdout");
        props.setProperty("batcher.com.netflix.logging.AsyncAppender.stdout.waitTimeinMillis", "120000");

        //LoggingConfiguration.getInstance().configure(props); // TODO error on initialize

    }

    @Test
    public void test() {

        Logger slflogger = LoggerFactory.getLogger(Blitz4jTest.class);
        slflogger.info("Testing named log with this string {}", "Test String");

        String monitoringData = getMonitoringData(consoleSummarizeEvent);

        Assert.assertNull(monitoringData);

//        Integer numSummarizedConsole =
//                Integer
//                    .valueOf(monitoringData);


    }

    @AfterClass
    public static void shutDown() {
        LoggingConfiguration.getInstance().stop();
    }

    private String getMonitoringData(String metricName) {
        Collection monitors = DefaultMonitorRegistry.getInstance().getRegisteredMonitors();
        for (Object m : monitors) {
            if (CompositeMonitor.class.isInstance(m)) {
                CompositeMonitor monitor = (CompositeMonitor) m;
                List<Monitor> monitorsList = monitor.getMonitors();
                for (Monitor m1 : monitorsList) {
                    if (metricName.equalsIgnoreCase(m1.getConfig().getName())) {
                        return m1.getValue() + "";
                    }

                }
            }

        }
        return null;
    }


}
