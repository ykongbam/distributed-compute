package com.ykongbam.task.mapReduce.executors.wordCount;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.ykongbam.task.PartialTaskResult;
import com.ykongbam.task.TaskExecutor;
import com.ykongbam.task.Tuple;
import com.ykongbam.task.mapReduce.shuffleSort.ShufflePartialTask;
import com.ykongbam.task.mapReduce.shuffleSort.ShuffleResponse;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: ykongbam (ykongbam@gmail.com)
 * Date: 23/06/17
 */

public class ShuffleExecutor implements TaskExecutor<ShufflePartialTask> {
    @Override
    public PartialTaskResult apply(ShufflePartialTask shufflePartialTask) {
        Collection<Tuple<Pair<String, Collection<String>>>> resultTuples = new HashSet<>();
        Collection<Tuple<Pair<String, String>>> tuples = shufflePartialTask.getTuples();
        Multimap<String, String> multimap = LinkedHashMultimap.create();

        for (Tuple<Pair<String, String>> inputTuple: tuples) {
            multimap.put(inputTuple.get().getKey(), inputTuple.get().getValue());
        }
        for (Map.Entry<String, Collection<String>> entry : multimap.asMap().entrySet()){
            resultTuples.add(new Tuple<>(ImmutablePair.of(entry.getKey(), entry.getValue())));
        }
        return new ShuffleResponse(resultTuples);
    }
}
