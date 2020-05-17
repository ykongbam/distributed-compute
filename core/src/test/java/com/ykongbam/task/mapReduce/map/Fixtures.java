package com.ykongbam.task.mapReduce.map;

import com.google.common.collect.ImmutableList;
import com.ykongbam.task.PartialTaskResult;
import com.ykongbam.task.Tuple;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class Fixtures {
    static List<Tuple<Pair<String, String>>> getInputTuples() {
        return ImmutableList.of(
                new Tuple<>(ImmutablePair.of("left", "right")),
                new Tuple<>(ImmutablePair.of("left2", "right2"))
        );
    }

    static PartialTaskResult<Pair<String, String>> getExpectedResponse() {
        return new MapperResponse(
                getExpectedTupleList()
        );
    }

    static ImmutableList<Tuple<Pair<String, String>>> getExpectedTupleList() {
        return ImmutableList.of(
                new Tuple<>(ImmutablePair.of("11", "11")),
                new Tuple<>(ImmutablePair.of("12", "12")),
                new Tuple<>(ImmutablePair.of("21", "21")),
                new Tuple<>(ImmutablePair.of("22", "22")),
                new Tuple<>(ImmutablePair.of("23", "23"))
        );
    }

}
