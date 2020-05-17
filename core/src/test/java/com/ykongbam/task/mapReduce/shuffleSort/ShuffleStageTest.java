package com.ykongbam.task.mapReduce.shuffleSort;

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

public class ShuffleStageTest {
    private ShuffleStage shuffleStage;

    //Shuffle executor not being mocked because the logic is tied to each other
    private ShuffleExecutor shuffleExecutor;

    @Mock
    private Splitter<String, String> splitter;
    @Mock
    private NodeManager nodemanager;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        this.shuffleExecutor = new ShuffleExecutor();
        this.shuffleStage = new ShuffleStage(nodemanager, shuffleExecutor, splitter);
    }

    @Test
    public void testShuffleSort() {
        List<Tuple<Pair<String, String>>> inputTuples = Fixtures.getInputTuples();

        MapReducePartialTask<String, String> partialTask1 = new MapReducePartialTask<>(
                ImmutableList.of(inputTuples.get(0), inputTuples.get(1), inputTuples.get(2))
        );
        MapReducePartialTask<String, String> partialTask2 = new MapReducePartialTask<>(
                ImmutableList.of(inputTuples.get(3), inputTuples.get(4))

        );

        ImmutableList<PartialTask> partialTasks = ImmutableList.of(partialTask1, partialTask2);

        when(splitter.splitInput(inputTuples)).thenReturn(partialTasks);

        when(nodemanager.submit(partialTasks, shuffleExecutor)).thenReturn(
                ImmutableList.of(
                        CompletableFuture.completedFuture(shuffleExecutor.apply(partialTask1)),
                        CompletableFuture.completedFuture(shuffleExecutor.apply(partialTask2))
        ));

        List<Tuple<Pair<String, Collection<String>>>> shuffleResponse = shuffleStage.shuffle(inputTuples);

        Assert.assertEquals(Fixtures.getExpectedTupleList(), shuffleResponse);
    }
}