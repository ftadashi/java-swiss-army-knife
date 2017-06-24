package com.apis.archaius;

import com.netflix.config.PollResult;
import com.netflix.config.PolledConfigurationSource;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ArchauisPolledConfigurationSource implements PolledConfigurationSource {

    public static final AtomicInteger ai = new AtomicInteger(0);

    public PollResult poll(boolean b, Object o) throws Exception {

        Map<String, Object> map = new ConcurrentHashMap<String, Object>();
        map.put("dynamic.prop", ai.incrementAndGet());
        return PollResult.createFull(map);

    }

}
