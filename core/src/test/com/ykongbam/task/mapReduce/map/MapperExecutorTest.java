package com.ykongbam.task.mapReduce.map;

import com.google.common.collect.ImmutableList;
import com.ykongbam.task.PartialTaskResult;
import com.ykongbam.task.Tuple;
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
    public void apply() {
        Collection<Tuple<Pair<String, String>>> inputTuples = ImmutableList.of(
                new Tuple<>(ImmutablePair.of("left", "right")),
                new Tuple<>(ImmutablePair.of("left2", "right2"))
        );
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
        PartialTaskResult response = mapperExecutor.apply(new MapPartialTask(inputTuples));

        PartialTaskResult<Pair<String, String>> expectedResponse = new MapperResponse(
                ImmutableList.of(
                        new Tuple<>(ImmutablePair.of("11", "11")),
                        new Tuple<>(ImmutablePair.of("12", "12")),
                        new Tuple<>(ImmutablePair.of("21", "21")),
                        new Tuple<>(ImmutablePair.of("22", "22")),
                        new Tuple<>(ImmutablePair.of("23", "23"))
                )
        );
        Assert.assertEquals(expectedResponse, response);
    }
}