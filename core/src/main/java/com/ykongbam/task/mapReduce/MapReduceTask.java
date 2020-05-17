package com.ykongbam.task.mapReduce;

import com.ykongbam.task.Task;
import com.ykongbam.task.Tuple;
import com.ykongbam.task.mapReduce.map.MapStage;
import com.ykongbam.task.mapReduce.reduce.ReduceStage;
import com.ykongbam.task.mapReduce.shuffleSort.ShuffleStage;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA.
 * User: ykongbam (ykongbam@gmail.com)
 * Date: 23/06/17
 */

@AllArgsConstructor
public class MapReduceTask implements Task {
    private final MapStage mapStage;
    private final ShuffleStage shuffleStage;
    private final ReduceStage reduceStage;
    private final List<Tuple<Pair<String, String>>> inputTuples;

    @Override
    public Future<MapReduceTaskResponse> process() {
        List<Tuple<Pair<String, String>>> intermediateOutput = mapStage.map(inputTuples);
        List<Tuple<Pair<String, Collection<String>>>> shuffleOutput = shuffleStage.shuffle(intermediateOutput);
        List<Tuple<Pair<String, String>>> response = reduceStage.reduce(shuffleOutput);
        return new Future<MapReduceTaskResponse>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public MapReduceTaskResponse get() throws InterruptedException, ExecutionException {
                return new MapReduceTaskResponse(response);
            }

            @Override
            public MapReduceTaskResponse get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return null;
            }
        };
    }


}
