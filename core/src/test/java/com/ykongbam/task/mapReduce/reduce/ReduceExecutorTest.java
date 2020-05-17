package com.ykongbam.task.mapReduce.reduce;

import com.google.common.collect.ImmutableList;
import com.ykongbam.task.PartialTaskResult;
import com.ykongbam.task.Tuple;
import com.ykongbam.task.mapReduce.MapReducePartialTask;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collection;

import static org.mockito.Mockito.when;

public class ReduceExecutorTest {
    private ReduceExecutor reduceExecutor;

    @Mock
    private Reducer<String, String, String, String> reducer;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.reduceExecutor = new ReduceExecutor(reducer);
    }

    @Test
    public void apply() {
        MapReducePartialTask<String, Collection<String>> inputTuples = new MapReducePartialTask<>(
                Fixtures.getInputTuples()
        );
        when(reducer.reduce("test", ImmutableList.of("1", "2")))
                .thenReturn(ImmutableList.of(ImmutablePair.of("test", "12")));
        when(reducer.reduce("test1", ImmutableList.of("2", "2")))
                .thenReturn(ImmutableList.of(ImmutablePair.of("test1", "22")));

        PartialTaskResult reduceResult = reduceExecutor.apply(inputTuples);
        ImmutableList<Tuple<ImmutablePair<String, String>>> expectedResponseTuples = ImmutableList.of(
                new Tuple<>(ImmutablePair.of("test", "12")),
                new Tuple<>(ImmutablePair.of("test1", "22"))
        );
        Assert.assertEquals(expectedResponseTuples, reduceResult.getTuples());

    }
}