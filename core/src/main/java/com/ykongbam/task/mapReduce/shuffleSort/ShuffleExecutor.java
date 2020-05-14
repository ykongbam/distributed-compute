package com.ykongbam.task.mapReduce.shuffleSort;

import com.ykongbam.task.PartialTaskResult;
import com.ykongbam.task.TaskExecutor;
import com.ykongbam.task.Tuple;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;


/**
 * Created with IntelliJ IDEA.
 * User: ykongbam (ykongbam@gmail.com)
 * Date: 23/06/17
 */

public class ShuffleExecutor implements TaskExecutor<ShufflePartialTask> {
    @Override
    public PartialTaskResult apply(ShufflePartialTask shufflePartialTask) {
        Collection<Tuple<Pair<String, String>>> tuples = shufflePartialTask.getTuples();

        PriorityQueue<Tuple<Pair<String, String>>> resultTuples = new PriorityQueue<>(
                tuples.size(),
                Comparator.comparing(t -> t.get().getKey())
        );

        resultTuples.addAll(tuples);

        return new ShuffleResponse(resultTuples);
    }
}
