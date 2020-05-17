package com.ykongbam.task.mapReduce.reduce;

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

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.when;

public class ReduceStageTest {
    private ReduceStage reduceStage;
    @Mock
    private Splitter<String, Collection<String>> splitter;
    @Mock
    private NodeManager nodemanager;
    @Mock
    private ReduceExecutor reduceExecutor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        reduceStage = new ReduceStage(nodemanager, reduceExecutor, splitter);
    }

    @Test
    public void testReduce() {
        List<Tuple<Pair<String, Collection<String>>>> inputTuples = Fixtures.getInputTuples();

        MapReducePartialTask<String, Collection<String>> partialTask1 = new MapReducePartialTask<>(
                ImmutableList.of(inputTuples.get(0))
        );
        MapReducePartialTask<String, Collection<String>> partialTask2 = new MapReducePartialTask<>(
                ImmutableList.of(inputTuples.get(1))

        );

        ImmutableList<PartialTask> partialTasks = ImmutableList.of(partialTask1, partialTask2);

        when(splitter.splitInput(inputTuples)).thenReturn(partialTasks);

        when(nodemanager.submit(partialTasks, reduceExecutor)).thenReturn(
                ImmutableList.of(CompletableFuture.completedFuture(Fixtures.getExpectedResponse())
        ));

        List<Tuple<Pair<String, String>>> reduceResponse = reduceStage.reduce(inputTuples);

        Assert.assertEquals(Fixtures.getExpectedTupleList(), reduceResponse);
    }
}