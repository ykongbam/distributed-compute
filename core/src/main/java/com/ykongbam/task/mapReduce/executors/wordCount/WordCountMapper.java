package com.ykongbam.task.mapReduce.executors.wordCount;

import com.ykongbam.task.mapReduce.map.Mapper;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WordCountMapper implements Mapper<String, String, String, String> {
    @Override
    public Collection<Pair<String, String>> map(String key, String value) {
        List<Pair<String, String>> response = new ArrayList<>();
        String[] split = value.split(" ");
        for(String string: split) {
            response.add(new ImmutablePair<>(string, Integer.toString(1)));
        }
        return response;
    }
}
