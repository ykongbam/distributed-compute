package com.ykongbam.task.mapReduce;

import com.google.common.collect.ImmutableList;
import com.ykongbam.task.Tuple;
import com.ykongbam.task.mapReduce.map.MapStage;
import com.ykongbam.task.mapReduce.reduce.ReduceStage;
import com.ykongbam.task.mapReduce.shuffleSort.ShuffleStage;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.mockito.Mockito.when;

public class MapReduceTaskTest {
    private MapReduceTask mapReduceTask;
    private List<Tuple<Pair<String, String>>> inputTuples;

    @Mock
    private MapStage mapStage;
    @Mock
    private ShuffleStage shuffleStage;
    @Mock
    private ReduceStage reduceStage;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        inputTuples = ImmutableList.of(
                new Tuple<>(ImmutablePair.of("key1", "value1")),
                new Tuple<>(ImmutablePair.of("key2", "value2"))
        );
        mapReduceTask = new MapReduceTask(mapStage, shuffleStage, reduceStage, inputTuples);
    }

    @Test
    public void testProcess() throws ExecutionException, InterruptedException {

        List<Tuple<Pair<String, String>>> mapOutput = ImmutableList.of(
                new Tuple<>(ImmutablePair.of("key1", "map1")),
                new Tuple<>(ImmutablePair.of("key2", "map2"))
        );
        when(mapStage.map(inputTuples)).thenReturn(mapOutput);


        List<Tuple<Pair<String, Collection<String>>>> shuffleOutput = ImmutableList.of(
                new Tuple<>(ImmutablePair.of("key1", ImmutableList.of("shuffle1"))),
                new Tuple<>(ImmutablePair.of("key2", ImmutableList.of("shuffle2")))
        );
        when(shuffleStage.shuffle(mapOutput)).thenReturn(shuffleOutput);

        List<Tuple<Pair<String, String>>> reduceOutput = ImmutableList.of(
                new Tuple<>(ImmutablePair.of("key1", "reduce1")),
                new Tuple<>(ImmutablePair.of("key2", "reduce2"))
        );
        when(reduceStage.reduce(shuffleOutput)).thenReturn(reduceOutput);

        Future<MapReduceTaskResponse> process = mapReduceTask.process();
        MapReduceTaskResponse expectedResponse = new MapReduceTaskResponse(reduceOutput);
        MapReduceTaskResponse actual = process.get();
        Assert.assertEquals(expectedResponse, actual);
    }
}