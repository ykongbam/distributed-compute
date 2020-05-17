package com.ykongbam.task.mapReduce.map;

import com.google.common.collect.ImmutableList;
import com.ykongbam.task.PartialTaskResult;
import com.ykongbam.task.Tuple;
import com.ykongbam.task.mapReduce.MapReducePartialTask;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collection;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class MapperExecutorTest {
    private MapperExecutor mapperExecutor;
    @Mock
    private Mapper<String, String, String, String> mapper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mapperExecutor =  new MapperExecutor(mapper);
    }

    @Test
    public void testApply() {
        Collection<Tuple<Pair<String, String>>> inputTuples = Fixtures.getInputTuples();

        when(mapper.map(eq("left"), eq("right"))).thenReturn(
                ImmutableList.of(
                        ImmutablePair.of("11","11"),
                        ImmutablePair.of("12", "12")
                )
        );
        when(mapper.map(eq("left2"), eq("right2"))).thenReturn(
                ImmutableList.of(
                        ImmutablePair.of("21","21"),
                        ImmutablePair.of("22", "22"),
                        ImmutablePair.of("23", "23")
                )
        );
        PartialTaskResult response = mapperExecutor.apply(new MapReducePartialTask<>(inputTuples));

        PartialTaskResult<Pair<String, String>> expectedResponse = Fixtures.getExpectedResponse();
        Assert.assertEquals(expectedResponse, response);
    }
}