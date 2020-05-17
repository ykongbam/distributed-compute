package com.ykongbam.task.mapReduce.executors.wordCount;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class WordCountReducerTest {
    private WordCountReducer wordCountReducer;

    @Before
    public void setUp() throws Exception {
        this.wordCountReducer = new WordCountReducer();
    }

    @Test
    public void reduce() {
        Collection<Pair<String, String>> response = wordCountReducer.reduce("test", ImmutableList.of("1", "2", "1"));
        ImmutableList<ImmutablePair<String, String>> expectedResponse = ImmutableList.of(ImmutablePair.of("test", "4"));
        Assert.assertEquals(expectedResponse, response);
    }
}