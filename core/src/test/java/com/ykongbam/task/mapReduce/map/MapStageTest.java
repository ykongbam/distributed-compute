package com.ykongbam.task.mapReduce.map;

import com.google.common.collect.ImmutableList;
import com.ykongbam.node.NodeManager;
import com.ykongbam.task.PartialTask;
import com.ykongbam.task.Tuple;
import com.ykongbam.task.mapReduce.MapReducePartialTask;
import com.ykongbam.task.mapReduce.splitter.Splitter;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.when;

public class MapStageTest {
    private MapStage mapStage;
    @Mock
    private Splitter<String, String> splitter;
    @Mock
    private NodeManager nodemanager;
    @Mock
    private MapperExecutor mapperExecutor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mapStage = new MapStage(nodemanager, mapperExecutor, splitter);
    }

    @Test
    public void testMap() {
        List<Tuple<Pair<String, String>>> inputTuples = Fixtures.getInputTuples();

        MapReducePartialTask<String, String> partialTask1 = new MapReducePartialTask<>(
                ImmutableList.of(inputTuples.get(0))
        );
        MapReducePartialTask<String, String> partialTask2 = new MapReducePartialTask<>(
                ImmutableList.of(inputTuples.get(1))

        );

        ImmutableList<PartialTask> partialTasks = ImmutableList.of(partialTask1, partialTask2);

        when(splitter.splitInput(inputTuples)).thenReturn(partialTasks);

        when(nodemanager.submit(partialTasks, mapperExecutor)).thenReturn(
                ImmutableList.of(CompletableFuture.completedFuture(Fixtures.getExpectedResponse())
        ));

        List<Tuple<Pair<String, String>>> mapResponse = mapStage.map(inputTuples);

        Assert.assertEquals(Fixtures.getExpectedTupleList(), mapResponse);
    }
}