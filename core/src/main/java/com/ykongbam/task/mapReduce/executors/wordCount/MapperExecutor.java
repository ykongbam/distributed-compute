package com.ykongbam.task.mapReduce.executors.wordCount;

import com.ykongbam.task.mapReduce.map.MapperResponse;
import com.ykongbam.task.PartialTaskResult;
import com.ykongbam.task.TaskExecutor;
import com.ykongbam.task.Tuple;
import com.ykongbam.task.mapReduce.map.MapPartialTask;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: ykongbam (ykongbam@gmail.com)
 * Date: 23/06/17
 */

public class MapperExecutor implements TaskExecutor<MapPartialTask> {
    @Override
    public PartialTaskResult apply(MapPartialTask mapPartialTask) {
        Collection<Tuple<Pair<String, String>>> resultTuples = new HashSet<>();
        Collection<Tuple<Pair<String, String>>> tuples = mapPartialTask.getTuples();
        //TODO tokenize values
        for (Tuple<Pair<String, String>> inputTuple : tuples) {
            resultTuples.add(new Tuple<>(new ImmutablePair<>(inputTuple.get().getValue(), Integer.toString(1))));
        }
        return new MapperResponse(resultTuples);
    }
}
