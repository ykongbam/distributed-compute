package com.ykongbam.task.mapReduce.reduce;

import com.ykongbam.task.PartialTaskResult;
import com.ykongbam.task.TaskExecutor;
import com.ykongbam.task.Tuple;
import com.ykongbam.task.mapReduce.MapReducePartialTask;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor
public class ReduceExecutor implements TaskExecutor<MapReducePartialTask<String, Collection<String>>> {
    private Reducer<String, String, String, String> reducer;

    @Override
    public PartialTaskResult apply(MapReducePartialTask<String, Collection<String>> reducePartialTask) {
        Collection<Tuple<Pair<String, String>>> resultTuples = new ArrayList<>();
        Collection<Tuple<Pair<String, Collection<String>>>> tuples = reducePartialTask.getTuples();

        for (Tuple<Pair<String, Collection<String>>> inputTuple : tuples) {
            reducer.reduce(inputTuple.get().getKey(), inputTuple.get().getValue())
                    .stream().map(Tuple::new)
                    .forEach(resultTuples::add);

        }

        return new ReduceResponse(resultTuples);
    }
}