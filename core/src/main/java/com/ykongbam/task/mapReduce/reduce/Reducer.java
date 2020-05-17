package com.ykongbam.task.mapReduce.reduce;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;

public interface Reducer<KeyIn, ValueIn, KeyOut, ValueOut> {
    Collection<Pair<KeyOut, ValueOut>> reduce(KeyIn key, Collection<ValueIn> value);
}
