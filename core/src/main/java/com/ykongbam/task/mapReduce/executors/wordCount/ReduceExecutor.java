package com.ykongbam.task.mapReduce.executors.wordCount;

import com.ykongbam.task.PartialTaskResult;
import com.ykongbam.task.TaskExecutor;
import com.ykongbam.task.Tuple;
import com.ykongbam.task.mapReduce.reduce.ReducePartialTask;
import com.ykongbam.task.mapReduce.reduce.ReduceResponse;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;
import java.util.HashSet;

public class ReduceExecutor implements TaskExecutor<ReducePartialTask> {
    @Override
    public PartialTaskResult apply(ReducePartialTask reducePartialTask) {
        Collection<Tuple<Pair<String, String>>> resultTuples = new HashSet<>();
        Collection<Tuple<Pair<String, Collection<String>>>> tuples = reducePartialTask.getTuples();

        for (Tuple<Pair<String, Collection<String>>> inputTuple : tuples) {
            int count = 0;
            for (String value : inputTuple.get().getValue()) {
                count += Integer.parseInt(value);
            }
            resultTuples.add(new Tuple<>(new ImmutablePair<>(inputTuple.get().getKey(), Integer.toString(count))));
        }
        return new ReduceResponse(resultTuples);
    }
}