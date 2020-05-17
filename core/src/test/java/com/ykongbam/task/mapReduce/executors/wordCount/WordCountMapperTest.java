package com.ykongbam.task.mapReduce.executors.wordCount;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

public class WordCountMapperTest {
    private WordCountMapper wordCountMapper;
    @Before
    public void setUp() throws Exception {
        this.wordCountMapper = new WordCountMapper();
    }

    @Test
    public void map() {
        Collection<Pair<String, String>> response = wordCountMapper.map("", "mary had a little little lamb");
        ImmutableList<ImmutablePair<String, String>> expectedResponse = ImmutableList.of(
                ImmutablePair.of("mary", "1"),
                ImmutablePair.of("had", "1"),
                ImmutablePair.of("a", "1"),
                ImmutablePair.of("little", "1"),
                ImmutablePair.of("little", "1"),
                ImmutablePair.of("lamb", "1")
        );
        Assert.assertEquals(expectedResponse, response);
    }
}