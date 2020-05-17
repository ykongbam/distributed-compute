package com.ykongbam.task.mapReduce.executors.wordCount;

import com.google.common.collect.ImmutableList;
import com.ykongbam.task.mapReduce.reduce.Reducer;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;

public class WordCountReducer implements Reducer<String, String, String, String> {
    @Override
    public Collection<Pair<String, String>> reduce(String key, Collection<String> values) {
        int totalCount = 0;
        for (String count : values) {
            totalCount += Integer.parseInt(count);
        }
        return ImmutableList.of(ImmutablePair.of(key, Integer.toString(totalCount)));
    }
}
