package com.apis.entity.fixture;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.apis.entity.Client;
import com.apis.entity.fixture.template.ClientTemplateLoader;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class FixFitureClientTest {

    @BeforeClass
    public static void setUp() {
        FixtureFactoryLoader.loadTemplates("com.apis.entity.fixture.template");
    }

    @Test
    public void testValidClient() {

        Client validClient =
                Fixture
                        .from(Client.class)
                        .gimme(ClientTemplateLoader.VALID);

        Assert.assertNotNull(validClient);
        Assert.assertTrue(validClient.getId() > 0);
        Assert.assertEquals(validClient.getScreenName(), validClient.getName() + validClient.getId());

    }

    @Test
    public void testInvalidClient() {

        Client invalidClient =
                Fixture
                        .from(Client.class)
                        .gimme(ClientTemplateLoader.INVALID);

        Assert.assertNotNull(invalidClient);
        Assert.assertNull(invalidClient.getBirthday());

    }
}
