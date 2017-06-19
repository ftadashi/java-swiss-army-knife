package com.apis.entity.orika;

import com.apis.entity.Client;
import com.apis.entity.dest.ClientDest;
import com.apis.entity.source.ClientSource;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

public class OrikaClientTest {

    private static MapperFactory mapperFactory;

    @BeforeClass
    public static void setUp() {

        mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(ClientSource.class, ClientDest.class)
                .field("id", "id")
                .field("name", "name")
                .field("screenName", "screenName")
                .field("email", "email")
                .field("birthday", "birthday")
                    .byDefault()
                        .register()
        ;

    }

    @Test
    public void test() {

        ClientSource source = new ClientSource();
        source.setId(1L);
        source.setName("Bill");
        source.setScreenName("bill1");
        source.setEmail("bill1@gmail.com");
        source.setBirthday(new Date(0));

        MapperFacade mapper = mapperFactory.getMapperFacade();
        ClientDest destination = mapper.map(source, ClientDest.class);

        Assert.assertEquals(source.getId(), destination.getId());
        Assert.assertEquals(source.getName(), destination.getName());
        Assert.assertEquals(source.getScreenName(), destination.getScreenName());
        Assert.assertEquals(source.getEmail(), destination.getEmail());
        Assert.assertEquals(source.getBirthday(), destination.getBirthday());

    }

}
