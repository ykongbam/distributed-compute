package com.ykongbam.task.mapReduce.reduce;

import com.google.common.collect.ImmutableList;
import com.ykongbam.task.PartialTaskResult;
import com.ykongbam.task.Tuple;
import com.ykongbam.task.mapReduce.map.MapperResponse;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;
import java.util.List;

public class Fixtures {
    static List<Tuple<Pair<String, Collection<String>>>> getInputTuples() {
        return ImmutableList.of(
                new Tuple<>(ImmutablePair.of("test", ImmutableList.of("1", "2"))),
                new Tuple<>(ImmutablePair.of("test1", ImmutableList.of("2", "2")))
        );
    }

    static PartialTaskResult<Pair<String, String>> getExpectedResponse() {
        return new ReduceResponse(
                getExpectedTupleList()
        );
    }

    static ImmutableList<Tuple<Pair<String, String>>> getExpectedTupleList() {
        return ImmutableList.of(
                new Tuple<>(ImmutablePair.of("test", "11")),
                new Tuple<>(ImmutablePair.of("test1", "12"))
        );
    }
}
