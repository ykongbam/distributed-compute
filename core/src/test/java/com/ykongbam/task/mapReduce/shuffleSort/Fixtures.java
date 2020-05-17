package com.ykongbam.task.mapReduce.shuffleSort;

import com.google.common.collect.ImmutableList;
import com.ykongbam.task.Tuple;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;
import java.util.List;

public class Fixtures {
    static List<Tuple<Pair<String, String>>> getInputTuples() {
        return ImmutableList.of(
                new Tuple<>(ImmutablePair.of("mary", "")),
                new Tuple<>(ImmutablePair.of("had", "")),
                new Tuple<>(ImmutablePair.of("a", "")),
                new Tuple<>(ImmutablePair.of("little", "")),
                new Tuple<>(ImmutablePair.of("lamb", ""))
        );
    }


    static List<Tuple<Pair<String, Collection<String>>>> getExpectedTupleList() {
        return ImmutableList.of(
                new Tuple<>(ImmutablePair.of("a", ImmutableList.of(""))),
                new Tuple<>(ImmutablePair.of("had", ImmutableList.of(""))),
                new Tuple<>(ImmutablePair.of("lamb", ImmutableList.of(""))),
                new Tuple<>(ImmutablePair.of("little", ImmutableList.of(""))),
                new Tuple<>(ImmutablePair.of("mary", ImmutableList.of("")))
        );
    }

}
