package com.example.google.dto;

import com.example.google.GoogelGuavaTestMethods;
import com.google.common.cache.CacheLoader;

/**
 * Created by jwt on 2019-8-14
 * <p>
 */
public class ExampleCache extends CacheLoader {

    @Override
    public Object load(Object o) throws Exception {
        return GoogelGuavaTestMethods.getFromDatabase(o.toString());
    }
}
