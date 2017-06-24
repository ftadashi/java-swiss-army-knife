package com.apis.archaius;

import com.netflix.config.DynamicIntProperty;
import com.netflix.config.DynamicLongProperty;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import org.junit.Assert;
import org.junit.Test;

public class ArchaiusLocalTest {

    @Test
    public void stringTypeTest() {

        DynamicStringProperty dsp =
                    DynamicPropertyFactory
                            .getInstance()
                            .getStringProperty("archaius.config.type.string", "");

        Assert.assertEquals(dsp.get(), "my string");

    }

    @Test
    public void integerTypeTest() {

        DynamicIntProperty dip =
                DynamicPropertyFactory
                        .getInstance()
                        .getIntProperty("archaius.config.type.integer", 0);

        Assert.assertEquals(dip.get(), 1);

    }

    @Test
    public void longTypeTest() {

        DynamicLongProperty dlp =
                DynamicPropertyFactory
                        .getInstance()
                        .getLongProperty("archaius.config.type.long", 0);

        Assert.assertEquals(dlp.get(), 2l);

    }

}
