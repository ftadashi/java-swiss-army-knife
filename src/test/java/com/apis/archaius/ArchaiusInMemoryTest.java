package com.apis.archaius;

import com.netflix.config.AbstractPollingScheduler;
import com.netflix.config.ConfigurationManager;
import com.netflix.config.DynamicConfiguration;
import com.netflix.config.DynamicIntProperty;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.FixedDelayPollingScheduler;
import com.netflix.config.PolledConfigurationSource;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ArchaiusInMemoryTest {

    @BeforeClass
    public static void setUp() {
        PolledConfigurationSource source = new ArchauisPolledConfigurationSource();
        AbstractPollingScheduler scheduler = new FixedDelayPollingScheduler(1000, 1000, true);
        DynamicConfiguration configuration = new DynamicConfiguration(source, scheduler);
        ConfigurationManager.install(configuration);
    }

    @Test
    public void testDynamicProp() throws InterruptedException {

        for (int i = 0; i < 5; i++) {

            DynamicIntProperty dip =
                    DynamicPropertyFactory
                    .getInstance()
                    .getIntProperty("dynamic.prop", 0);

            System.out.println(String.format("%s = %s", dip.get(), ArchauisPolledConfigurationSource.ai.get()));
            Assert.assertEquals(dip.get(), ArchauisPolledConfigurationSource.ai.get());

            Thread.sleep(1000);

        }

    }

}
