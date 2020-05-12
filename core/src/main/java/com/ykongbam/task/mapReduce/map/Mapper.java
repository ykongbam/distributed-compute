package com.ykongbam.task.mapReduce.map;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;

public interface Mapper<KeyIn, ValueIn, KeyOut, ValueOut> {
    Collection<Pair<KeyOut, ValueOut>> map(KeyIn key, ValueIn value);
}
