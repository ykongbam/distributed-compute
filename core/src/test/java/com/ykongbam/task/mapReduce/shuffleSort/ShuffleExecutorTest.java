package com.ykongbam.task.mapReduce.shuffleSort;

import com.google.common.collect.ImmutableList;
import com.ykongbam.task.Tuple;
import com.ykongbam.task.mapReduce.MapReducePartialTask;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.PriorityQueue;

public class ShuffleExecutorTest {
    private ShuffleExecutor shuffleExecutor;

    @Before
    public void setUp() throws Exception {
        shuffleExecutor = new ShuffleExecutor();
    }

    @Test
    public void testApply() {
        List<Tuple<Pair<String, String>>> inputTuples = ImmutableList.of(
                new Tuple<>(ImmutablePair.of("mary", "")),
                new Tuple<>(ImmutablePair.of("had", "")),
                new Tuple<>(ImmutablePair.of("a", "")),
                new Tuple<>(ImmutablePair.of("little", "")),
                new Tuple<>(ImmutablePair.of("lamb", ""))
        );
        ShuffleResponse response = (ShuffleResponse) shuffleExecutor.apply(new MapReducePartialTask<>(inputTuples));
        PriorityQueue<Tuple<Pair<String, String>>> tuples = response.getTuples();

        Assert.assertEquals(new Tuple<>(ImmutablePair.of("a", "")), tuples.poll());
        Assert.assertEquals(new Tuple<>(ImmutablePair.of("had", "")), tuples.poll());
        Assert.assertEquals(new Tuple<>(ImmutablePair.of("lamb", "")), tuples.poll());
        Assert.assertEquals(new Tuple<>(ImmutablePair.of("little", "")), tuples.poll());
        Assert.assertEquals(new Tuple<>(ImmutablePair.of("mary", "")), tuples.poll());
    }
}