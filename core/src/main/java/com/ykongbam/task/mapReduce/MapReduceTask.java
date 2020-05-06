package com.ykongbam.task.mapReduce;

import com.google.common.collect.Iterables;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.ykongbam.task.Task;
import com.ykongbam.task.mapReduce.executors.wordCount.ShuffleExecutor;
import com.ykongbam.task.mapReduce.map.MapperResponse;
import com.ykongbam.node.NodeManager;
import com.ykongbam.task.PartialTask;
import com.ykongbam.task.PartialTaskResult;
import com.ykongbam.task.Tuple;
import com.ykongbam.task.mapReduce.executors.wordCount.MapperExecutor;
import com.ykongbam.task.mapReduce.map.MapPartialTask;
import com.ykongbam.task.mapReduce.shuffleSort.ShufflePartialTask;
import com.ykongbam.task.mapReduce.shuffleSort.ShuffleResponse;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: ykongbam (ykongbam@gmail.com)
 * Date: 23/06/17
 */

@AllArgsConstructor
public class MapReduceTask implements Task {
    NodeManager nodeManager;
    MapperExecutor mapperExecutor;
    ShuffleExecutor shuffleExecutor;
    private Set<Tuple<Pair<String, String>>> inputTuples;

    @Override
    public Future<MapReduceTaskResponse> process() {
        Set<Tuple<Pair<String, String>>> intermediateOutput = map();
        Set<Tuple<Pair<String, Collection<String>>>> shuffleOutput = shuffle(intermediateOutput);
        Set<Tuple<Pair<String, String>>> response = reduce(shuffleOutput);
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
                return null;
            }

            @Override
            public MapReduceTaskResponse get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return null;
            }
        };
    }

    private Set<Tuple<Pair<String, String>>> map() {
        Set<Tuple<Pair<String, String>>> response = new HashSet<>();
        Set<PartialTask> partialTasks = splitInput();
        Set<Future<PartialTaskResult>> mapperResposeFutures = nodeManager.submit(partialTasks, mapperExecutor);
        for (Future<PartialTaskResult> partialTaskResultFuture : mapperResposeFutures) {
            try {
                MapperResponse partialTaskResult = (MapperResponse) partialTaskResultFuture.get();
                for (Tuple<Pair<String, String>> tuple : partialTaskResult.getTuples()) {
                    response.add(tuple);
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    private Set<PartialTask> splitInput() {
        int activeNodeCount = nodeManager.getActiveNodeCount();
        int partitionSize = inputTuples.size() / activeNodeCount;
        Set<PartialTask> partialTasks = new HashSet<>();
        for (List<Tuple<Pair<String, String>>> partialTuples : Iterables.partition(inputTuples, partitionSize)) {
            partialTasks.add(new MapPartialTask(partialTuples));
        }
        return partialTasks;
    }

    private Set<Tuple<Pair<String, Collection<String>>>> shuffle(Set<Tuple<Pair<String, String>>> intermediateOutput) {
        Set<Tuple<Pair<String, Collection<String>>>> response = new HashSet<>();
        Set<PartialTask> partialTasks =  splitShuffle(intermediateOutput);
        Set<Future<PartialTaskResult>> shuffleResponseFuture = nodeManager.submit(partialTasks, shuffleExecutor);
        for (Future<PartialTaskResult> partialTaskResultFuture : shuffleResponseFuture) {
            try {
                ShuffleResponse partialTaskResult = (ShuffleResponse) partialTaskResultFuture.get();
                for (Tuple<Pair<String, Collection<String>>> tuple : partialTaskResult.getTuples()) {
                    response.add(tuple);
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    private Set<PartialTask> splitShuffle(Set<Tuple<Pair<String, String>>> intermediateOutput) {
        Multimap<Integer, Tuple<Pair<String, String>>> buckets = LinkedHashMultimap.create();
        for (Tuple<Pair<String, String>> tuple : intermediateOutput) {
            buckets.put(tuple.get().getKey().hashCode() % nodeManager.getActiveNodeCount(), tuple);
        }
        return buckets.asMap().entrySet().stream()
                .map(entry -> new ShufflePartialTask(entry.getValue()))
                .collect(Collectors.toSet());
    }

    private Set<Tuple<Pair<String,String>>> reduce(Set<Tuple<Pair<String, Collection<String>>>> shuffleOutput) {
        return null;
    }
}
